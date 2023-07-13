package com.joyldp.jwtpracticebusinessserver.authentication.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.joyldp.jwtpracticebusinessserver.authentication.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthenticationServerProxy {

    @Value("${auth.server.base.url}")
    private String baseUrl;

    private RestTemplate restTemplate;

    public AuthenticationServerProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public void sendAuth(String username, String password) {
        String url = baseUrl + "/user/auth";
        log.info("bkf: sendAuth(): url: {}", url);
        var body = new User();
        body.setUsername(username);
        body.setPassword(password);
        var request = new HttpEntity<>(body);
        restTemplate.postForEntity(url, request, Void.class);
    }
    
    public boolean sendOtp(String username, String code) {
        String url = baseUrl + "/otp/check";
        var body = new User();
        body.setUsername(username);
        body.setCode(code);
        var request = new HttpEntity<>(body);
        var response = restTemplate.postForEntity(url, request, Void.class);
        return response.getStatusCode().equals(HttpStatus.OK);
    }

}
