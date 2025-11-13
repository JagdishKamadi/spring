package com.epam.netty_server_demo.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NettyServer {
    public void start(int listenerPort) throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline()
                                    .addLast(new StringDecoder(),
                                            new StringEncoder(),
                                            new SimpleChannelInboundHandler<String>() {
                                                @Override
                                                protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                                    log.info("Received from client: {}", msg);
                                                    String response = "ACK from Server: " + msg.toUpperCase();
                                                    ctx.writeAndFlush(response);
                                                }
                                            });
                        }
                    });

            ChannelFuture future = serverBootstrap.bind(listenerPort).sync();
            log.info("Netty Server started on port {}", listenerPort);
            future.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
