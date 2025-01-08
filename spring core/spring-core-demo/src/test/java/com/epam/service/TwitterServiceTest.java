package com.epam.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TwitterServiceTest {

    @Mock
    private MessageService messageService;

    @Test
    public void testSendMessage() {
        String message = "I am back";
        messageService.sendMsg(message);
        verify(messageService).sendMsg(message);
    }
}