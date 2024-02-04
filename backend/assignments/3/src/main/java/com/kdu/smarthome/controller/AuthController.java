package com.kdu.smarthome.controller;

import com.kdu.smarthome.config.CustomUserDetails;
import com.kdu.smarthome.dto.UserDTO;
import com.kdu.smarthome.dto.response.AuthResponse;
import com.kdu.smarthome.filter.JWTAuthFilter;
import com.kdu.smarthome.filter.JWTTokenGenerator;
import com.kdu.smarthome.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthController {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private CustomUserDetails customUserDetails;

    @Autowired
    AuthController(UserService userService, AuthenticationManager authenticationManager,CustomUserDetails customUserDetails) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.customUserDetails=customUserDetails;
    }


    /**
     * @param user
     * @return
     * ○ message (Type: String) - A success message.
     * ○ token (Type: String) - The authentication token for the registered user
     */
    @PostMapping("register")
    public HttpEntity<AuthResponse> addUser(@RequestBody UserDTO user, HttpServletResponse response) {
        log.info("Received request to add a new user: {}", user);
        String pass = user.getPassword();
        try {
            log.info("password "+pass);
            user.setRole("ROLE_USER");
            if(user.getName()==null)
            {
                user.setName(user.getFirstName()+user.getLastName());
            }
            userService.addUser(user);
            log.info("from request body" +user);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(),pass));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = customUserDetails.loadUserByUsername(user.getUsername());
            log.info(userDetails.toString());
            String jwtToken= JWTTokenGenerator.generateToken(authentication,response);
            if(jwtToken==null)
            {
                throw new NullPointerException("Null jwt token");
            }
            log.info("User {} added successfully", user.getUsername());
            AuthResponse responseVal = new AuthResponse("User added successfully", jwtToken, HttpStatus.OK);
            return ResponseEntity.ok(responseVal);
        } catch (Exception e) {
            AuthResponse responseVal = new AuthResponse("Unable to add user"+e, null, HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseVal);
        }
    }
}
