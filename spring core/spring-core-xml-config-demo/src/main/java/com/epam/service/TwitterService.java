package com.epam.service;

import org.springframework.stereotype.Component;

@Component("twitterServiceCustomBean")
public class TwitterService implements MessageService {

    public TwitterService() {
        System.out.println("TwitterService bean created");
    }

    @Override
    public void processMessage(String message) {
        System.out.println("In class TwitterService");
        System.out.println(message);
    }
}
