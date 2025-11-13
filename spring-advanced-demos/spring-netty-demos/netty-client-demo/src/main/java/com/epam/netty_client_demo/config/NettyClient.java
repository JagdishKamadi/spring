package com.epam.netty_client_demo.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class NettyClient {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String sendMessage(Object message) throws InterruptedException, JsonProcessingException {
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
                                    .addLast(new ByteArrayDecoder(),  // Converts incoming ByteBuf → byte[]
                                            new ByteArrayEncoder(),
                                            new SimpleChannelInboundHandler<byte[]>() {
                                                @Override
                                                protected void channelRead0(ChannelHandlerContext ctx, byte[] bytes) {
                                                    response.append(new String(bytes, StandardCharsets.UTF_8));
                                                    log.info("Received response from server: {}", new String(bytes, StandardCharsets.UTF_8));
                                                }
                                            });
                        }
                    });

            Channel channel = bootstrap.connect("localhost", 8081).sync().channel();
            byte[] bytes = objectMapper.writeValueAsBytes(message);
            channel.writeAndFlush(bytes).sync();
            Thread.sleep(300);
            channel.close().sync();
        } finally {
            group.shutdownGracefully();
        }

        return response.toString();
    }
}
