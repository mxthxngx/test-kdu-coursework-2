package com.example.springbootapidemo.controller;

import com.example.springbootapidemo.model.UserEntity;
import com.example.springbootapidemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves details of a single user.
     *
     * @param  model   the model containing user details
     * @return         the user entity with details
     */
    @GetMapping("/user/details")
    public UserEntity singleUserDetails(ModelMap model) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userName = auth.getName();

            UserEntity user = userService.getUserByUsername(userName);

            if (user != null) {
                log.info("User details retrieved successfully for user: {}", userName);
                return user;
            } else {
                log.warn("No user found for username: {}", userName);
                return null;
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching user details: {}", e.getMessage(), e);
            throw e;
        }
    }
}
