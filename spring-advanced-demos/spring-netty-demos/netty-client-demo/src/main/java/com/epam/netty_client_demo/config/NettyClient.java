package com.epam.netty_client_demo.config;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NettyClient {
    public String sendMessage(String message) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        final StringBuilder response = new StringBuilder();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline()
                                    .addLast(new StringDecoder(),
                                            new StringEncoder(),
                                            new SimpleChannelInboundHandler<String>() {
                                                @Override
                                                protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                                    response.append(msg);
                                                    log.info("Received response from server: {}", msg);
                                                }
                                            });
                        }
                    });

            Channel channel = bootstrap.connect("localhost", 8081).sync().channel();
            channel.writeAndFlush(message).sync();
            Thread.sleep(200); // small delay for response
            channel.close().sync();
        } finally {
            group.shutdownGracefully();
        }

        return response.toString();
    }
}
