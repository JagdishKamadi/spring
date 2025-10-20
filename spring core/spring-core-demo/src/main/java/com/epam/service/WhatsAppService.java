package com.epam.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class WhatsAppService implements MessageService {

    @Override
    public void sendMsg(String message) {
        System.out.println(message);
    }
}
