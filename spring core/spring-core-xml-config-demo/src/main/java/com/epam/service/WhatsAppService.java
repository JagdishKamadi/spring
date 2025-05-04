package com.epam.service;

public class WhatsAppService implements MessageService {
    @Override
    public void processMessage(String message) {
        System.out.println("In class WhatsAppService");
        System.out.println(message);
    }
}
