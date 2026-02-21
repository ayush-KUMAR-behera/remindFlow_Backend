package com.ayush.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ayush.dto.AuthResponse;
import com.ayush.dto.LoginRequest;
import com.ayush.dto.RegisterRequest;
import com.ayush.entity.User;
import com.ayush.exception.BadRequestException;
import com.ayush.exception.ResourceNotFoundException;
import com.ayush.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // ===============================
    // REGISTER
    
    public void register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail().trim().toLowerCase());
        user.setPassword(
                passwordEncoder.encode(request.getPassword()));
        user.setActive(true);

        userRepository.save(user);
    }

    // ===============================
    // LOGIN
   
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail().trim())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(
        		request.getPassword(),
                user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        // ⭐ ACCOUNT DISABLE CHECK
        if (!user.isActive()) {
            throw new BadRequestException("Account is disabled");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }

  
    // DISABLE ACCOUNT
    
    public void disableAccount(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        user.setActive(false);

        userRepository.save(user);
    }
}
