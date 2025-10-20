package com.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessorImpl implements MessageProcessor {

    // we are using here match-by-name bean
    @Autowired
    private MessageService whatsAppService;

    @Override
    public void processMsg(String message) {
        System.out.println("Bean type " + whatsAppService.getClass());
        whatsAppService.sendMsg(message);
    }
}