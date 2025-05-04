package com.epam.service;

public class MessageSecurityServiceImpl implements MessageSecurityService {
    private final MessageProcessor messageProcessor;

    public MessageSecurityServiceImpl(MessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
    }

    @Override
    public void validateMessageSecurity() {
        try {
            System.out.println("Checking the message security...");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Message is secure and checking for further step");
        messageProcessor.processMessage("Processing message");
    }
}
