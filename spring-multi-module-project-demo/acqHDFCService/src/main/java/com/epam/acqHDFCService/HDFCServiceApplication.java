package com.epam.acqHDFCService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.epam.*")
public class HDFCServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HDFCServiceApplication.class, args);
    }
}
