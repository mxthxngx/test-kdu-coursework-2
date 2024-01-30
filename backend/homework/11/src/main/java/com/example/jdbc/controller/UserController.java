package com.example.jdbc.controller;

import com.example.jdbc.dto.UserDTO;
import com.example.jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/add")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
        try {
            userService.addUser(userDTO);
            return ResponseEntity.ok("User added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to add user: " + e.getMessage());
        }
    }
    @GetMapping("/user/getbytenant")
    public ResponseEntity<List<UserDTO>> getUsersByTenant(@RequestParam UUID tenantID) {
        try {
            List<UserDTO> users = userService.getUsersByTenant(tenantID);
            if (users != null) {
                return ResponseEntity.ok(users);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PutMapping("/user/update")
    public ResponseEntity<String> updateUser(@RequestParam String username,@RequestParam UUID userid) {
        try {
            userService.updateUser(username,userid); // Implement this method in your service layer
            return ResponseEntity.ok("User updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to update user: " + e.getMessage());
        }
    }
}
