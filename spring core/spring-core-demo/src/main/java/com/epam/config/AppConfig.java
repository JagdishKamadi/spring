package com.epam.config;

import com.epam.service.TwitterService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// basePackageClasses:- it will scan the all classes from that class level and onwards
@ComponentScan(basePackageClasses = TwitterService.class)
public class AppConfig {
}
