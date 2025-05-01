package com.epam.service;

import org.springframework.stereotype.Component;

@Component
public class WhatsAppService implements MessageService {
    @Override
    public void sendMsg(String message) {
        System.out.println(message);
    }
}
