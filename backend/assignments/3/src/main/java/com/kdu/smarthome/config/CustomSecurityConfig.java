package com.kdu.smarthome.config;

import com.kdu.smarthome.filter.JWTAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Configuration class for custom security settings.
 */
@Configuration
@EnableWebSecurity
public class CustomSecurityConfig {

    /**
     * Configures a security filter chain with custom default filters.
     *
     * @param  http  the HttpSecurity object
     * @return      the built SecurityFilterChain object
     */
    @Bean
    SecurityFilterChain customDefaultFilter(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(jwtAuthFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/v1/house", "/api/v1/house/**", "/api/v1/room/**", "/api/v1/room",
                                "/api/v1/inventory", "/api/v1/inventory/**", "/api/v1/device/**").hasAuthority("ROLE_USER")
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .anyRequest().authenticated())
                .csrf().disable()
                .cors();
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    /**
     * Creates an AuthenticationManager bean.
     *
     * @param authenticationConfiguration the AuthenticationConfiguration object
     * @return the AuthenticationManager instance
     * @throws Exception if an error occurs while creating the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Returns a new BCryptPasswordEncoder instance.
     *
     * @return the BCryptPasswordEncoder instance
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates a JWTAuthFilter bean.
     *
     * @return the JWTAuthFilter instance
     */
    @Bean
    public JWTAuthFilter jwtAuthFilter() {
        return new JWTAuthFilter();
    }
}
