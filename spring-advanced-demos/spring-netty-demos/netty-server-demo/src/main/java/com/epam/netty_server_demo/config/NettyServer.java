package com.epam.netty_server_demo.config;

import com.epam.lib_netty_shared.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NettyServer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void start(int listenerPort) throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, worker).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast(new ByteArrayDecoder(), new ByteArrayEncoder(), new SimpleChannelInboundHandler<byte[]>() {
                        @Override
                        protected void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
                            log.info("Received message from client {}", msg);
                            if (msg != null) {
                                Person person = objectMapper.readValue(msg, Person.class);
                                log.info("Received Person: {}", person);
                                person.setFirstname(person.getFirstname().toUpperCase());
                                person.setLastname(person.getLastname().toUpperCase());
                                byte[] bytes = objectMapper.writeValueAsBytes(person);
                                ctx.writeAndFlush(bytes);
                            }
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
