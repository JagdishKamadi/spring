package com.epam.journal_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // to enable the scheduler
public class JournalAppApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(JournalAppApplication.class, args);
        Environment environment = context.getEnvironment();
        System.out.println("\n Currently active profile : " + environment.getActiveProfiles()[0] +
                " and running on port :" + environment.getProperty("server.port") + "\n");
    }

}
