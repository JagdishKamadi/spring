package com.epam.netty_client_demo.controller;

import com.epam.netty_client_demo.config.NettyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/netty-client")
@Slf4j
public class MessageController {
    private final NettyClient nettyClient;

    public MessageController(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }

    @GetMapping(value = "/message/{message}")
    public String sendMessage(@PathVariable String message) throws InterruptedException {
        return nettyClient.sendMessage(message);
    }
}
