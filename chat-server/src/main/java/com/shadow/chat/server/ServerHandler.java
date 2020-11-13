package com.shadow.chat.server;

import com.shadow.chat.bean.client.ReqMessage;
import com.shadow.chat.bean.client.ReqSingleDataDTO;
import com.shadow.chat.bean.client.ReqUserLoginDTO;
import com.shadow.chat.bean.constant.Chat;
import com.shadow.chat.bean.server.RespMessage;
import com.shadow.chat.bean.server.RespSingleDataDTO;
import com.shadow.chat.bean.server.RespSystemDataDTO;
import io.netty.buffer.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务器消息处理器
 *
 * @author Shadow
 * @date 2020/11/02 11:53:08
 */
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 用户、channel map
     */
    private static ConcurrentHashMap<String, Channel> USER_CHANNEL_GROUP = new ConcurrentHashMap<>();
    /**
     * 群组、channel map
     */
    private static ConcurrentHashMap<String, List<Channel>> GROUP_CHANNEL_GROUP = new ConcurrentHashMap<>();
    /**
     * channelId，channel map
     */
    private static ConcurrentHashMap<String, Channel> CHANNEL_GROUP = new ConcurrentHashMap<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        CHANNEL_GROUP.put(ctx.channel().id().asLongText(), ctx.channel());
        log.info("channelActive | 连接加入：{}，当前连接数：{}", getAddress(ctx).getHostString(), CHANNEL_GROUP.size());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        CHANNEL_GROUP.remove(ctx.channel().id().asLongText());
        log.info("channelInactive | 连接断开{}，当前连接数：{}", getAddress(ctx).getHostString(), CHANNEL_GROUP.size());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println(msg);
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] data = new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(data);
//        System.out.println(data.length);
//        Thread.sleep(1000 * 60 * 60 * 24 * 365);
        if (true) {
            return;
        }
        try {
            ReqMessage message = (ReqMessage) msg;
            log.info("channelRead | 收到{}消息：{}", getAddress(ctx), msg);
            try {
                //命令类型
                String cmd = message.getCmd();
                if (Chat.Cmd.LOGIN.equals(cmd)) {
                    ReqUserLoginDTO user = (ReqUserLoginDTO) message.getData();
                    USER_CHANNEL_GROUP.put(user.getUserAccount(), ctx.channel());
                    //响应数据
                    RespMessage respMessage = new RespMessage();
                    //系统消息
                    respMessage.setCmd(Chat.Cmd.SYSTEM);

                    RespSystemDataDTO respSystemDataDTO = new RespSystemDataDTO();
                    respSystemDataDTO.setMsg(user.getNickName() + "上线啦！");
                    respMessage.setData(respSystemDataDTO);

                    log.info("channelRead | 响应数据到客户端：{}", respMessage);
                    //通知所有人，有账号上线了
                    broadcast(null, respMessage);
                } else if (Chat.Cmd.SINGLE.equals(cmd)) {
                    ReqSingleDataDTO reqSingleDataDTO = (ReqSingleDataDTO) message.getData();
                    Channel channel = USER_CHANNEL_GROUP.get(reqSingleDataDTO.getTargetUserAccount());
                    if (channel != null) {
                        //响应数据
                        RespMessage respMessage = new RespMessage();
                        //发送给单个人
                        respMessage.setCmd(Chat.Cmd.SINGLE);
                        //把接收的数据转成响应数据对象
                        RespSingleDataDTO respSingleDataDTO = new RespSingleDataDTO();
                        respSingleDataDTO.setMsg(reqSingleDataDTO.getMsg());
                        respSingleDataDTO.setMsgType(reqSingleDataDTO.getMsgType());
                        respSingleDataDTO.setTargetUserAccount(reqSingleDataDTO.getTargetUserAccount());
                        respSingleDataDTO.setUserAccount(reqSingleDataDTO.getUserAccount());
                        respMessage.setData(respSingleDataDTO);

                        channel.writeAndFlush(respMessage);
                    } else {
                        log.error("channelRead | 消息发送失败，对方账号 不存在：{}", reqSingleDataDTO.getTargetUserAccount());
                    }
                } else {
                    log.warn("channelRead | 其他类型消息，暂不处理", cmd);
                }
            } catch (Exception e) {
                log.error("channelRead | 收到异常消息：{}", msg);
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("exceptionCaught | 发生异常：{}", cause.getMessage(), cause);
    }

    public static InetSocketAddress getAddress(ChannelHandlerContext ctx) {
        return (InetSocketAddress) ctx.channel().remoteAddress();
    }

    /**
     * 广播消息
     *
     * @param channelId   连接对象，如果不为空，就不发送给该对象
     * @param respMessage 响应数据
     */
    public static void broadcast(String channelId, RespMessage respMessage) {
        CHANNEL_GROUP.forEach((e, v) -> {
            if (channelId != null) {
                if (!e.equals(channelId)) {
                    v.writeAndFlush(respMessage);
                }
            } else {
                v.writeAndFlush(respMessage);
            }
        });
    }

    public static void main(String[] args) {

        byte[] data = {0x00,0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08,0x09};
        ByteBuf buf = Unpooled.buffer();
        buf.ensureWritable(20);
        System.out.println(buf);
        buf.writeBytes(data);
        System.out.println(buf.touch());

    }

}
