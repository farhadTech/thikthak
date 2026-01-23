package com.thikthak.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Allowed frontend domain
        config.setAllowedOrigins(List.of("https://thikthak.netlify.app",
                "http://localhost:5173"
        ));

        // Allowed HTTP methods
        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));

        // Allowed headers
        config.setAllowedHeaders(List.of("*"));

        // Allow credentials (cookies, auth headers)
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
