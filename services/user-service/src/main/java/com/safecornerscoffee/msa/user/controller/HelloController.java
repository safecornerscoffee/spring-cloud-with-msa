package com.safecornerscoffee.msa.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(@Value("${spring.application.name}") String appName) {
        return String.format("hello, %s", appName);
    }
}
