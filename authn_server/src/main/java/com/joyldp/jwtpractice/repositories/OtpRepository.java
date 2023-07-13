package com.joyldp.jwtpractice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joyldp.jwtpractice.entities.Otp;

public interface OtpRepository extends JpaRepository<Otp, String> {
    Optional<Otp> findOtpByUsername(String username);
}
