package com.joyldp.jwtpracticebusinessserver.model;

import lombok.Data;

@Data
public class OtpDto {
    private String username;
    private String code;
}
