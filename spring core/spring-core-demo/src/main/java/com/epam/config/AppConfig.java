package com.epam.config;

import com.epam.service.TwitterService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = TwitterService.class)
public class AppConfig {
}
