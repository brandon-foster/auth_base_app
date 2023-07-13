package com.joyldp.jwtpractice.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyldp.jwtpractice.entities.Otp;
import com.joyldp.jwtpractice.entities.User;
import com.joyldp.jwtpractice.services.UserService;

@RestController
public class AuthController {
    private final UserService userService;
    
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/user/add")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }
    
    @PostMapping("/user/auth")
    public void authUser(@RequestBody User user) {
        userService.auth(user);
    }
    
    @PostMapping("/otp/check")
    public void checkUser(@RequestBody Otp otp, HttpServletResponse response) {
        if (userService.check(otp)) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
    
}
