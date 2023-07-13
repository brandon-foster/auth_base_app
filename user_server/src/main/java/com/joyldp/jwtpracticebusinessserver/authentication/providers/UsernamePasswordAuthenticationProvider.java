package com.joyldp.jwtpracticebusinessserver.authentication.providers;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.joyldp.jwtpracticebusinessserver.authentication.UsernamePasswordAuthentication;
import com.joyldp.jwtpracticebusinessserver.authentication.proxy.AuthenticationServerProxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    
    private AuthenticationServerProxy proxy;
    
    public UsernamePasswordAuthenticationProvider(AuthenticationServerProxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        log.info("bkf: authenticate(): authentication.getName(): {}", authentication.getName());
        String password = String.valueOf(authentication.getCredentials());
        log.info("bkf: authenticate(): authentication.getCredentials(): {}", authentication.getCredentials());
        log.info("bkf: authenticate(): invoking proxy.sendAuth(username, password)");
        proxy.sendAuth(username, password);
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthentication.class.isAssignableFrom(authentication);
    }
    
}
