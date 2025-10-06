package com.epam.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "World") String name, Model model) {
        model.addAttribute("userName", name);
        return "hello";
    }
}
