package com.thikthak.backend.config;

import com.thikthak.backend.entity.Admin;
import com.thikthak.backend.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AdminInitConfig {
    @Bean
    CommandLineRunner initAdmin(AdminRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (!repo.existsById("admin")) {
                Admin admin = new Admin();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                repo.save(admin);
            }
        };
    }
}
