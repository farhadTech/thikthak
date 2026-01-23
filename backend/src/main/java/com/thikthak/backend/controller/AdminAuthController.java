package com.thikthak.backend.controller;

import com.thikthak.backend.entity.Admin;
import com.thikthak.backend.repository.AdminRepository;
import com.thikthak.backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin req) {
        System.out.println("Login attempt: " + req.getUsername());

        Admin admin = repo.findById(req.getUsername())
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        System.out.println("Admin found: " + admin.getUsername());

        if (!passwordEncoder.matches(req.getPassword(), admin.getPassword())) {
            System.out.println("Password mismatch");
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(admin.getUsername());
        System.out.println("JWT generated: " + token);
        return ResponseEntity.ok(Map.of("token", token));
    }

}
