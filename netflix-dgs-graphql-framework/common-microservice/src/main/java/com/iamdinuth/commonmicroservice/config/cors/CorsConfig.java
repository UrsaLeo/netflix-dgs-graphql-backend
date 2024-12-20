package com.iamdinuth.commonmicroservice.config.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow credentials (cookies, authorization headers)
        config.setAllowCredentials(true);

        // Set allowed origins (you can set your frontend URL here)
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Adjust with your frontend URL

        // Set allowed HTTP methods (GET, POST, OPTIONS)
        config.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS"));

        // Set allowed headers (such as Content-Type, Authorization)
        config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

        // Apply the CORS configuration globally to all endpoints (or customize to specific endpoint if needed)
        source.registerCorsConfiguration("/graphql", config);
        return new CorsFilter(source);
    }
}