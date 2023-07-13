package com.joyldp.jwtpracticebusinessserver.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.joyldp.jwtpracticebusinessserver.authentication.model.User;
import com.joyldp.jwtpracticebusinessserver.model.LoginDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/signup")
public class SignupController {
    
    @Value("${auth.server.base.url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public SignupController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public boolean signup(@RequestBody LoginDto loginDto) {
        log.info("received loginDto: {}", loginDto);
        final String url = baseUrl + "/user/add";
        var body = new User();
        body.setUsername(loginDto.getUsername());
        body.setPassword(loginDto.getPassword());
        var request = new HttpEntity<>(body);
        var response = restTemplate.postForEntity(url, request, Void.class);
        return response.getStatusCode().equals(HttpStatus.OK);
    }

}
