package com.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessorImpl implements MessageProcessor {


    private MessageService messageService;

    @Autowired
    public void setMessageService(@Qualifier("whatsAppService") MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void processMsg(String message) {
        messageService.sendMsg(message);
    }
}