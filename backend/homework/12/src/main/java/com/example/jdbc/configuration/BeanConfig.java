package com.example.jdbc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
@Configuration
public class BeanConfig {

    @Bean
    public UUID uuid() {
        return UUID.randomUUID();
    }
}
