package com.epam.welcome;

import org.springframework.stereotype.Component;

@Component
public class Greeting {

    public void sayHello()
    {
        System.out.println("Hi, Jagdish Application has started at port number 8082");
    }
}
