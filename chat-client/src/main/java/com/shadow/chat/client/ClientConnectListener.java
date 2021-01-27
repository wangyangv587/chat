package com.shadow.chat.client;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author Shadow
 * @date 2020/11/13 14:15:34
 */
@Slf4j
public class ClientConnectListener implements ChannelFutureListener {
    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        if (!channelFuture.isSuccess()) {
            final EventLoop loop = channelFuture.channel().eventLoop();
            loop.schedule(() -> {
                log.error("operationComplete | 重新连接服务器");
                Client.run("localhost", 8888);
            }, 5, TimeUnit.MILLISECONDS);
        } else {
            Client.channel = channelFuture.channel();
            log.info("operationComplete | 重新连接服务器成功");
        }
    }
}
