package com.epam;

import com.epam.config.AppConfig;
import com.epam.service.MessageProcessor;
import com.epam.service.MessageService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MessageProcessor userService = (MessageProcessor) applicationContext.getBean("messageProcessorImpl");
        userService.processMsg("twitter message sending ");

        MessageService messageServiceBean = (MessageService) applicationContext.getBean("twitterService");
        messageServiceBean.sendMsg("Message received");

        applicationContext.close();
    }
}
