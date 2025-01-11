package com.epam.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/information")
public class MyAppController {

    @Value("${app.name}")
    String name;
    @Value("${version}")
    String version;

    // how to set default value
    @Value("${releasedate : Not Mention yet}")
    String releaseDate;

    @GetMapping
    public String getAppInformation() {
        return "App Name : " + name + "\n" + "Version : " + version+"\n"+"Release Date : "+releaseDate;
    }
}
