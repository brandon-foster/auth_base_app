package com.joyldp.jwtpractice.services;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyldp.jwtpractice.entities.Otp;
import com.joyldp.jwtpractice.entities.User;
import com.joyldp.jwtpractice.repositories.OtpRepository;
import com.joyldp.jwtpractice.repositories.UserRepository;
import com.joyldp.jwtpractice.utils.GenerateCodeUtil;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final OtpRepository otpRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, OtpRepository otpRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.otpRepository = otpRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void auth(User user) {
        Optional<User> o = userRepository.findUserByUsername(user.getUsername());
        if (o.isPresent()) {
            User u = o.get();
            if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
                renewOtp(u);
            }
            else {
                throw new BadCredentialsException("Bad credentials.");
            }
        }
        else {
            throw new BadCredentialsException("Bad credentials.");
        }
    }

    public boolean check(Otp otpToValidate) {
        Optional<Otp> storedOtp = otpRepository.findOtpByUsername(otpToValidate.getUsername());
        if (storedOtp.isPresent()) {
            return otpToValidate.getCode().equals(storedOtp.get().getCode());
        }
        return false;
    }

    private void renewOtp(User user) {
        String code = GenerateCodeUtil.generateCode();
        Optional<Otp> userOtp = otpRepository.findOtpByUsername(user.getUsername());
        if (userOtp.isPresent()) {
            Otp otp = userOtp.get();
            otp.setCode(code);
            otpRepository.save(otp);
        }
        else {
            Otp otp = new Otp();
            otp.setUsername(user.getUsername());
            otp.setCode(code);
            otpRepository.save(otp);
        }
    }

}
