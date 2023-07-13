package com.joyldp.jwtpractice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joyldp.jwtpractice.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByUsername(String username);
}
