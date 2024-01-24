package com.example.demo;

import com.example.demo.model.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
@Configuration
public class AppConfig {
    @Bean
    public List<Vehicle> vehicleList() {
        return new ArrayList<>();
    }
}
