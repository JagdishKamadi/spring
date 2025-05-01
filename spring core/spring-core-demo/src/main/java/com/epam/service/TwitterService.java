package com.epam.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
/**
 * If we do not specify any specific bean name
 * then the TwitterService bean will injected as we are using @Primary annotation for below class
 */
@Primary
public class TwitterService implements MessageService {

    @Override
    public void sendMsg(String message) {
        System.out.println(message);
    }
}