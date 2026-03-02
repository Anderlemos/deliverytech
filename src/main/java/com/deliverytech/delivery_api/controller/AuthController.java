package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

        String username = request.get("username");
        String password = request.get("password");

        // Aqui você vai validar no banco depois
        if (!"admin".equals(username) || !"123".equals(password)) {
            return ResponseEntity.status(401).build();
        }

        String token = jwtService.generateToken(username);

        return ResponseEntity.ok(Map.of("token", token));
    }
}