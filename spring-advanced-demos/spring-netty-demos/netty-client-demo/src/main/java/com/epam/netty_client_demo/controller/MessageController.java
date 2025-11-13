package com.epam.netty_client_demo.controller;

import com.epam.lib_netty_shared.model.Person;
import com.epam.netty_client_demo.config.NettyClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/netty-client")
@Slf4j
public class MessageController {
    private final NettyClient nettyClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MessageController(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }

    @PostMapping(value = "/save")
    private Person savePerson(@RequestBody Person person) throws InterruptedException, JsonProcessingException {
        String string = nettyClient.sendMessage(person);
        return objectMapper.readValue(string, Person.class);
    }
}
