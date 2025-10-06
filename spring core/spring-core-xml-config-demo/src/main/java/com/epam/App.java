package com.epam;


import com.epam.service.MessageSecurityServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-config.xml");

        MessageSecurityServiceImpl messageSecurityServiceImplBean = (MessageSecurityServiceImpl) context.getBean("messageSecurityServiceImplBean");
        messageSecurityServiceImplBean.validateMessageSecurity();
        context.close();
    }
}
