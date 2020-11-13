package com.shadow.chat.client;

import com.shadow.chat.bean.client.ReqMessage;
import com.shadow.chat.bean.client.ReqUserLoginDTO;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Shadow
 * @date 2020/11/02 11:53:08
 */
@Slf4j
public class Client {

    private static Channel channel;

    public static void main(String[] args) {
        run("localhost", 8888);
    }

    public static void run(String host, int port) {
        run(host, port, null);
    }

    public static void run(String host, int port, String userAccount) {
        if (channel != null && channel.isActive()) {
            ChannelFuture channelFuture = channel.close();
            channelFuture.addListener((e) -> {
                log.info("run | 关闭连接成功，重新连接");
                run0(host, port, userAccount);
            });
        } else {
            run0(host, port, userAccount);
        }

    }

    public static void run0(String host, int port, String userAccount) {
        NioEventLoopGroup work = new NioEventLoopGroup(1);
        Bootstrap b = new Bootstrap();

        try {
            log.info("run | 连接到服务器，host：{}，port：{}", host, port);
            b.group(work);
            b.channel(NioSocketChannel.class);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {

                    ch.pipeline()
                            .addLast(new ObjectDecoder(1024 * 1024, ClassResolvers.cacheDisabled(this.getClass().getClassLoader())))
                            .addLast(new ObjectEncoder())
                            .addLast(new ClientHandler());
                }
            });
            ChannelFuture conn = b.connect(host, port).sync();
            conn.addListener((e) -> {
                if (e.isSuccess()) {
                    channel = conn.channel();
                    ReqMessage reqMessage = new ReqMessage();
                    ReqUserLoginDTO user = new ReqUserLoginDTO();
                    user.setUserAccount(userAccount);
                    user.setNickName(userAccount);
                    reqMessage.setCmd("LOGIN");
                    reqMessage.setData(user);
                    reqMessage.setChannelId(channel.id().asLongText());
                    ChannelFuture channelFuture = channel.writeAndFlush(reqMessage);
                    channelFuture.addListener((k) -> {
//                        UserController.connState.countDown();
                    });
                } else {
                    log.info("run | 连接失败");
                }
            });
            conn.channel().closeFuture().sync().addListener((future -> log.info("run | 连接断开")));
        } catch (InterruptedException e) {
            log.error("run | 连接失败:{}", e.getMessage(), e);
        } finally {
            work.shutdownGracefully();
        }
    }

    public static void sendMsg(ReqMessage msg) {
        log.info("sendMsg | 发送数据：{}", msg);
        if (channel == null || !channel.isActive()) {
            throw new RuntimeException("还未登录，请登录！");
        } else {
            channel.writeAndFlush(msg);
        }
    }
}
