package com.epam.service;

public class MessageProcessorImpl implements MessageProcessor {

    private final MessageService twitterService;
    private final MessageService whatsAppService;
    private MessageService messageService;

    public MessageProcessorImpl(MessageService twitterService, MessageService whatsAppService) {
        System.out.println("Calling the constructor injection");
        this.twitterService = twitterService;
        this.whatsAppService = whatsAppService;
    }

    @Override
    public void processMessage(String message) {
        twitterService.processMessage(message);
        whatsAppService.processMessage(message);
        messageService.processMessage(message);
    }

    public void setMessageService(MessageService messageService) {
        System.out.println("Calling the setter injection");
        this.messageService = messageService;
    }
}
