package com.epam.service;

public class MessageProcessorImpl implements MessageProcessor {

    private final MessageService twitterService;
    private final MessageService whatsAppService;

    public MessageProcessorImpl(MessageService twitterService, MessageService whatsAppService) {
        this.twitterService = twitterService;
        this.whatsAppService = whatsAppService;
    }

    @Override
    public void processMessage(String message) {
        twitterService.processMessage(message);
        whatsAppService.processMessage(message);
    }
}
