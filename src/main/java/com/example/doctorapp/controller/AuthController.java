package com.example.doctorapp.controller;

import com.example.doctorapp.dto.LoginRequest;
import com.example.doctorapp.model.User;
import com.example.doctorapp.service.UserService;
import com.example.doctorapp.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    // Register new user
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        // TODO: Ideally encrypt password here before saving
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // Login existing user and return JWT token
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOpt = userService.getUserByEmail(loginRequest.getEmail());

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        User user = userOpt.get();

        // For demo, plain password check; replace with BCryptPasswordEncoder for production
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        String token = jwtTokenProvider.generateToken(user.getEmail(), user.getRole());
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}
