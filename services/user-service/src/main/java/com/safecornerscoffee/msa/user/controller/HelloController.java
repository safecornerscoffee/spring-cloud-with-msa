package com.safecornerscoffee.msa.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping("/hello")
    public String hello(@Value("${spring.application.name}") String appName) {
        return String.format("hello, %s", appName);
    }

    @GetMapping("/message")
    public String message(@Value("${spring.application.name}") String appName,
                          @RequestHeader("user-service-request") String header) {
        return String.format("appName: %s\n user-service-request-header: %s", appName, header);
    }

}
