package com.hello.jenkins_demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping
    public String hello() {
        return "Hello World";
    }
}
