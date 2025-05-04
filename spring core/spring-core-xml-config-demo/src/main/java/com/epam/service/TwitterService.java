package com.epam.service;

public class TwitterService implements MessageService {
    @Override
    public void processMessage(String message) {
        System.out.println("In class TwitterService");
        System.out.println(message);
    }
}
