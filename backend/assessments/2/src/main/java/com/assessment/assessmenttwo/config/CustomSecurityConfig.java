package com.assessment.assessmenttwo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import com.assessment.assessmenttwo.filter.*;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class CustomSecurityConfig {

    /**
     * A security filter chain with custom default filter.
     *
     * @param  http  the HttpSecurity object
     * @return      the built SecurityFilterChain object
     */
    @Bean
    SecurityFilterChain customDefaultFilter(HttpSecurity http) throws Exception {
        http.
                addFilterAfter(new TokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new TokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/user/login","/user/register").permitAll()
                        .requestMatchers("/inventory/**").hasRole("ADMIN")
                        .requestMatchers("/shoppingcart/**","/order/**").hasRole("USER")
                        .anyRequest().authenticated()).csrf().disable().cors();
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    /**
     * Returns a new BCryptPasswordEncoder instance.
     *
     * @return         	the BCryptPasswordEncoder instance
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}