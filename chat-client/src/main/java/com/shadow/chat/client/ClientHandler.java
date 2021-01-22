package com.shadow.chat.client;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.shadow.chat.bean.Message;
import com.shadow.chat.bean.SerializationUtil;
import com.shadow.chat.bean.User;
import com.shadow.chat.bean.constant.Chat;
import com.shadow.chat.bean.server.RespMessage;
import com.shadow.chat.bean.server.RespSingleDataDTO;
import com.shadow.chat.bean.server.RespSystemDataDTO;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @author Shadow
 * @date 2020/11/02 11:53:08
 */

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelActive | 连接成功{}", getAddress(ctx).getHostString());
        for (int i = 0; i < 100; i++) {
//            byte[] serializer = SerializationUtil.serializer(user);
            byte[] bytes = "names".getBytes(StandardCharsets.UTF_8);
            Message message = new Message();
            message.setLength(bytes.length);
            message.setContent(bytes);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        log.info("channelInactive | 连接断开{}", getAddress(ctx).getHostString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        Message data = (Message)msg;
        String s = new String(data.getContent(), CharsetUtil.UTF_8);
        System.out.println(s);
        /*try {
            log.info("channelRead | 收到消息：{}", msg);
            RespMessage respMessage = (RespMessage) msg;
            if (respMessage.getCmd().equals(Chat.Cmd.SINGLE)) {
                RespSingleDataDTO respSingleDataDTO = (RespSingleDataDTO) respMessage.getData();
                log.info("收到来自{}的消息：{}", respSingleDataDTO.getUserAccount(), respSingleDataDTO.getMsg());

            } else if (respMessage.getCmd().equals(Chat.Cmd.SYSTEM)) {
                RespSystemDataDTO respSystemDataDTO = (RespSystemDataDTO) respMessage.getData();
                log.info("收到系统通知：{}", respSystemDataDTO.getMsg());
            } else {
                log.info("其他消息");
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }*/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("exceptionCaught | 发生异常：{}", cause.getMessage(), cause);
    }

    public static InetSocketAddress getAddress(ChannelHandlerContext ctx) {
        return (InetSocketAddress) ctx.channel().remoteAddress();
    }

}
