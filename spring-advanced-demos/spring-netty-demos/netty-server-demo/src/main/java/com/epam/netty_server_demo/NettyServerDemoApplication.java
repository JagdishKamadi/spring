package com.epam.netty_server_demo;

import com.epam.netty_server_demo.config.NettyServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NettyServerDemoApplication implements CommandLineRunner {

    private final NettyServer nettyServer;

    public NettyServerDemoApplication(NettyServer nettyServer) {
        this.nettyServer = nettyServer;
    }

    public static void main(String[] args) {
        SpringApplication.run(NettyServerDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        nettyServer.start(8081);
    }
}
