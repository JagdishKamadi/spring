package com.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessorImpl implements MessageProcessor {

    // we are using here match-by-name bean
    @Autowired
    @Qualifier(value = "whatsAppService")
    private MessageService whatsAppService1;

    @Autowired
    @Qualifier(value = "whatsAppService")
    private MessageService whatsAppService2;

    @Override
    public void processMsg(String message) {
        // after using the @Scope annotation, it will create the 2 different hashcode for 2 object
        System.out.println("Bean type whatsAppService1" + whatsAppService1.hashCode());
        System.out.println("Bean type whatsAppService2" + whatsAppService2.hashCode());

    }
}