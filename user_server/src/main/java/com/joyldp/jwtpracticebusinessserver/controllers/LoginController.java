package com.joyldp.jwtpracticebusinessserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.joyldp.jwtpracticebusinessserver.authentication.OtpAuthentication;
import com.joyldp.jwtpracticebusinessserver.authentication.UsernamePasswordAuthentication;
import com.joyldp.jwtpracticebusinessserver.model.LoginDto;
import com.joyldp.jwtpracticebusinessserver.model.OtpDto;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Slf4j
@RestController
public class LoginController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Value("${jwt.signing.key}")
    private String signingKey;

    @PostMapping("/api/userpass")
    public ResponseEntity<Authentication> userpass(@RequestBody LoginDto loginDto) {
        log.info("bkf: userpass()");
        Authentication authentication = new UsernamePasswordAuthentication(loginDto.getUsername(), loginDto.getPassword());
        authenticationManager.authenticate(authentication);
        return ResponseEntity.ok()
                .body(authentication);
    }
    
    @PostMapping("/api/otp")
    public ResponseEntity<Authentication> otp(@RequestBody OtpDto otpDto, HttpServletResponse response) {
        log.info("bkf: otp()");
        Authentication authentication = new OtpAuthentication(otpDto.getUsername(), otpDto.getCode());
        authentication = authenticationManager.authenticate(authentication);
        SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
        String jwt = Jwts.builder()
                .setClaims(Map.of("username", otpDto.getUsername()))
                .signWith(key)
                .compact();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwt);
        Cookie cookie = new Cookie("jwt", jwt);
        // cookie.setHttpOnly(true);
        // cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
        return ResponseEntity.ok()
                .headers(headers)
                .body(authentication);
    }
    
}
