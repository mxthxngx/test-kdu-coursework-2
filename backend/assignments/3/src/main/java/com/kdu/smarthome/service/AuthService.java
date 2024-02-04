package com.kdu.smarthome.service;

import com.kdu.smarthome.config.CustomUserDetails;
import com.kdu.smarthome.dto.UserDTO;
import com.kdu.smarthome.dto.response.AuthResponse;
import com.kdu.smarthome.filter.JWTTokenGenerator;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetails customUserDetails;

    @Autowired
    public AuthService(UserService userService, AuthenticationManager authenticationManager,
                       CustomUserDetails customUserDetails) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.customUserDetails = customUserDetails;
    }

    /**
     * Registers a new user and generates a JWT token upon successful registration.
     *
     * @param user     The user information to be registered.
     * @param response The HTTP servlet response object.
     * @return ResponseEntity containing the authentication response.
     */
    public ResponseEntity<AuthResponse> registerUser(UserDTO user, HttpServletResponse response) {
        log.info("Received request to add a new user: {}", user);
        String pass = user.getPassword();
        try {
            log.info("password " + pass);
            user.setRole("ROLE_USER");
            if (user.getName() == null) {
                user.setName(user.getFirstName() + user.getLastName());
            }
            userService.addUser(user);
            log.info("from request body" + user);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), pass));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = customUserDetails.loadUserByUsername(user.getUsername());
            log.info(userDetails.toString());
            String jwtToken = JWTTokenGenerator.generateToken(authentication,user.getRole(), response);
            if (jwtToken == null) {
                throw new NullPointerException("Null jwt token");
            }
            log.info("User {} added successfully", user.getUsername());
            AuthResponse responseVal = new AuthResponse("User added successfully", jwtToken, HttpStatus.OK);
            return ResponseEntity.ok(responseVal);
        } catch (Exception e) {
            AuthResponse responseVal = new AuthResponse("Unable to add user" + e, null, HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseVal);
        }
    }
}
