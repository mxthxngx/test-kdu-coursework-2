package com.caching.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

/**
 * The entry point for the Caching Service application.
 * <p>
 * This application demonstrates caching functionality using Spring Boot and Caffeine Cache.
 * </p>
 */
@SpringBootApplication
@EnableCaching
public class CachingApplication {

    /**
     * The main method to start the application.
     * <p>
     * By default, it runs with the "dev" profile. Profiles can also be set via runtime arguments
     * (e.g., `-Dspring.profiles.active=prod`).
     * </p>
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // Allow setting profiles via environment variables or runtime arguments for flexibility
        String activeProfile = System.getProperty("spring.profiles.active", "dev");

        new SpringApplicationBuilder(CachingApplication.class)
                .profiles(activeProfile)
                .run(args);
    }
}