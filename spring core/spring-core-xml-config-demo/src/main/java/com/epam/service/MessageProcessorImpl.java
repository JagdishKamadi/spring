package com.epam.service;

public class MessageProcessorImpl implements MessageProcessor {

    private MessageService twitterService;
    private MessageService whatsAppService;
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

    /**
     * Note here we are using the setter based injection
     * that's means in the method setMessageServiceBeanUsingTwitterServiceImplementation first 'set' name is going to ignore while giving the name to bean id in xml config
     * and after that it will follow the camelCase naming convention for property based bean id
     * example:
     * for setter method setMessageServiceBeanUsingTwitterServiceImplementation bean id in xml configuration would be 'messageServiceBeanUsingTwitterServiceImplementation'
     *
     * @param messageService
     */
    public void setMessageServiceBeanUsingTwitterServiceImplementation(MessageService messageService) {
        System.out.println("Calling the setter injection");
        this.messageService = messageService;
    }
}
