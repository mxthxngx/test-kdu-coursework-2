package com.example.springbootapidemo.controller;

import com.example.springbootapidemo.model.UserEntity;
import com.example.springbootapidemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Slf4j
public class AdminController {

    @Autowired
    AdminController(UserService userService)
    {
        this.userService=userService;
    }
    UserService userService;

    /**
     * A method to display all users.
     *
     * @return         list of UserEntity
     */
    @GetMapping("/admin/displayAllUsers")
    public List<UserEntity> displayAllUsers() {
        log.info("Received request to display all users");
        try {
            List<UserEntity> users = userService.displayAllUsers();
            log.info("Retrieved {} user(s) from the database", users.size());
            return users;
        } catch (Exception e) {
            log.error("Error occurred while fetching users: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Adds a new user using the provided UserEntity object.
     *
     * @param  user   the UserEntity object to be added
     * @return       a ResponseEntity containing a success or error message
     */
    @PostMapping("/admin/add")
    public HttpEntity<String> addUser(@RequestBody UserEntity user) {
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
