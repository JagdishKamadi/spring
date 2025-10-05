package com.epam.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/entry")
public class SimpleController {

    @Value("${spring.application.name}")
    String appName;

    @RequestMapping(value = "/sayHi", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> sayHiToUser() {
        return ResponseEntity.ok("<h1>Hi Jack!</h1>");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }
}
