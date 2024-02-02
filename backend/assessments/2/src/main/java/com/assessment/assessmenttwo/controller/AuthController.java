package com.assessment.assessmenttwo.controller;

import com.assessment.assessmenttwo.dto.UserDTO;
import com.assessment.assessmenttwo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {
    UserService userService;
    @Autowired
    AuthController(UserService userService)
    {
        this.userService=userService;
    }
    /**
     * Method to handle the login endpoint for person.
     *
     * @return         	an ResponseEntity with a String body
     */
    @GetMapping("/user/login")
    public ResponseEntity<String> login(){
        return new ResponseEntity<>("test login", HttpStatus.CREATED);
    }

    @PostMapping("/user/register")
    public HttpEntity<String> addUser(@RequestBody UserDTO user) {
        log.info("Received request to add a new user: {}", user);
        try {

            userService.addUser(user);
            log.info("User {} added successfully", user.getUsername());
            return new ResponseEntity<>("User added successfully", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while adding user {}: {}", user.getUsername(), e.getMessage());
            return new ResponseEntity<>("Unable to add user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
