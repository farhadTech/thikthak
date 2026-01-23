package com.thikthak.backend.util;

import com.thikthak.backend.entity.Admin;
import com.thikthak.backend.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(AdminRepository repo, PasswordEncoder encoder) {
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
