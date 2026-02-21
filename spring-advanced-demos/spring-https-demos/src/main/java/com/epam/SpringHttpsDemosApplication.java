package com.epam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "com.epam.*")
@Configuration
@EnableAutoConfiguration
public class SpringHttpsDemosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringHttpsDemosApplication.class, args);
    }

}
