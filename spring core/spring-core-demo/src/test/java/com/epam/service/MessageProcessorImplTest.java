package com.epam.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class MessageProcessorImplTest {

    @Mock
    private MessageService messageService;

    @Mock
    private MessageProcessor messageProcessor;

    private String message;

    @BeforeEach
    public void setUpObject() {
        message = "Hello jack, Welcome to Jumanji";
    }


    @Test
    public void testProcessMsg() {

        messageService.sendMsg(message);
        verify(messageService).sendMsg(message);

        messageProcessor.processMsg(message);
        verify(messageProcessor).processMsg(message);
    }

}