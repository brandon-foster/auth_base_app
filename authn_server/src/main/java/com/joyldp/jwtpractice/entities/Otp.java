package com.joyldp.jwtpractice.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Otp {

    @Id
    private String username;
    private String code;

}
