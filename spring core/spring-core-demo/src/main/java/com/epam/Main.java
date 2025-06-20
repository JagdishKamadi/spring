package com.epam;

import com.epam.config.AppConfig;
import com.epam.service.MessageProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MessageProcessor userService = (MessageProcessor) applicationContext.getBean("messageProcessorImpl");
        userService.processMsg("What's app message sending ");

        applicationContext.close();
    }
}
