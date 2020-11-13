package com.shadow.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
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
public class Server {

    public static void main(String[] args) {
        new Server().start(8888);
    }

    public void start(int port) {
        //boss线程组，用来处理客户端连接
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        //work线程组，用来处理客户端的业务
        NioEventLoopGroup work = new NioEventLoopGroup();
        //启动辅助类
        ServerBootstrap b = new ServerBootstrap();
        try {
            log.info("启动服务器，port：{}", port);
            b.group(boss, work);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    ch.pipeline()
                            //使用Java的编解码方式
//                            .addLast(new ObjectDecoder(1024 * 1024, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())))
//                            .addLast(new ObjectEncoder())
                            .addLast(new ServerHandler());
                }
            });
            b.option(ChannelOption.SO_BACKLOG, 128);
            b.option(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture sync = b.bind(port).sync();
            sync.addListener((e) -> {
                log.info("服务器启动成功，port：{}", port);
            });
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.info("服务器启动失败：{}", e.getMessage());
        } finally {
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }
}
