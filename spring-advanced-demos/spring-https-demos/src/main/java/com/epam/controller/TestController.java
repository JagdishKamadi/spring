package com.epam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class TestController {

    @GetMapping("/hello")
    public ResponseEntity<String> greeting() {
        return ResponseEntity.ok("Hello John Snow!");
    }
}
