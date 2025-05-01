package com.mega;

import com.epam.service.CategoryService;
import com.epam.service.MessageService;

public class RequestBuilder {

    public void show() {
        MessageService messageServiceBean = CategoryService.getMessageServiceBean();
        messageServiceBean.sendMsg("I am jagdish");
    }
}
