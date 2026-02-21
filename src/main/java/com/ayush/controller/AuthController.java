package com.ayush.controller;

import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.ayush.dto.AuthResponse;
import com.ayush.dto.LoginRequest;
import com.ayush.dto.RegisterRequest;
import com.ayush.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request) {

        authService.register(request);

        return new ResponseEntity<>(
                "Registered Successfully",
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {

        AuthResponse response = authService.login(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    
    @PatchMapping("/disable")
    public ResponseEntity<String> disableAccount(
            Authentication authentication) {

        authService.disableAccount(authentication.getName());

        return ResponseEntity.ok("Account disabled successfully");
    }
}
