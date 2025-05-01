package com.epam.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CategoryService {
    private static MessageService messageService;

    public CategoryService(@Qualifier("twitterService") MessageService service) {
        messageService = service;
    }

    public static MessageService getMessageServiceBean() {
        System.out.println("In class Category Service Bean type is " + messageService.getClass());
        return messageService;
    }

}
