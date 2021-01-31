package com.holidu.interview.assignment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    // Usually I use HealthEndpoint, but no need to add actuator starter for 1 endpoing
    @GetMapping("/")
    public String health() {
        return "Hello world!";
    }
}
