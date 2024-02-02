package com.assessment.assessmenttwo.controller;

import com.assessment.assessmenttwo.dto.UserDTO;
import com.assessment.assessmenttwo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
    UserService userService;
    @Autowired
    UserController(UserService userService)
    {
        this.userService = userService;
    }
    @PostMapping("/user/register")
            public String addUser(UserDTO userDTO)
    {
        try {
            userService.save(userDTO);
            log.info("User saved!");
            return "User saved";
        }
        catch (Exception e)
        {
            throw e;
        }

    }
}
