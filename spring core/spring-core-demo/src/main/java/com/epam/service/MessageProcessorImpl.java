package com.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessorImpl implements MessageProcessor {

    // we are using here match-by-name bean
    @Autowired
    private MessageService whatsAppServiceBean;


    @Override
    public void processMsg(String message) {
        System.out.println("Bean type " + whatsAppServiceBean.getClass());
        whatsAppServiceBean.sendMsg(message);
    }
}