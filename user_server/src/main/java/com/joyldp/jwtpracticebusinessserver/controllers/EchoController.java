package com.joyldp.jwtpracticebusinessserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/echo")
public class EchoController {
    
    @GetMapping
    public String echo() {
        return "echo";
    }
    
}
