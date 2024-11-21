package com.example.project.controller;

import com.example.project.dto.request.UserRequestDTO;
import com.example.project.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDTO userRequestDTO) {
        userService.register(userRequestDTO.getUsername(), userRequestDTO.getPassword(), "ROLE_USER");
        return ResponseEntity.ok("Kullanıcı başarıyla kaydedildi");
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
        return ResponseEntity.ok("Login successful for user: " + authentication.getName());
    }
}