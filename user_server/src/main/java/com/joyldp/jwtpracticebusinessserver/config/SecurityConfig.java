package com.joyldp.jwtpracticebusinessserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

// import com.joyldp.jwtpracticebusinessserver.authentication.filters.InitialAuthenticationFilter;
import com.joyldp.jwtpracticebusinessserver.authentication.filters.JwtAuthenticationFilter;
import com.joyldp.jwtpracticebusinessserver.authentication.providers.OtpAuthenticationProvider;
import com.joyldp.jwtpracticebusinessserver.authentication.providers.UsernamePasswordAuthenticationProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // private final InitialAuthenticationFilter initialAuthenticationFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final OtpAuthenticationProvider otpAuthenticationProvider;
    private final UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    // public SecurityConfig(InitialAuthenticationFilter initialAuthenticationFilter,
    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            OtpAuthenticationProvider otpAuthenticationProvider,
            UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider) {
        // this.initialAuthenticationFilter = initialAuthenticationFilter;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.otpAuthenticationProvider = otpAuthenticationProvider;
        this.usernamePasswordAuthenticationProvider = usernamePasswordAuthenticationProvider;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(otpAuthenticationProvider)
            .authenticationProvider(usernamePasswordAuthenticationProvider);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
            // .addFilterAt(initialAuthenticationFilter, BasicAuthenticationFilter.class)
            .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
        http.authorizeRequests()
            .antMatchers("/api/signup", "/api/userpass", "/api/otp").permitAll()
            .anyRequest().authenticated();
    }
    
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
