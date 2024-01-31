/**
 * The UserController class handles HTTP requests related to users.
 * It defines endpoints for adding, retrieving, updating, and retrieving all users.
 */
package com.example.jdbc.controller;

import com.example.jdbc.dto.UserDTO;
import com.example.jdbc.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;

    /**
     * Constructor for UserController class.
     * @param userService the UserService instance to handle business logic related to users.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint for adding a new user.
     * @param userDTO the UserDTO object containing information about the user to be added.
     * @return a ResponseEntity with a success message if the user is added successfully, or an error message otherwise.
     */
    @PostMapping("/user/add")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
        try {
            userService.addUser(userDTO);
            return ResponseEntity.ok("User added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to add user: " + e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving users by tenant ID.
     * @param tenantID the UUID of the tenant to retrieve users for.
     * @return a ResponseEntity containing a list of UserDTO objects if successful, or an error message otherwise.
     */
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

    /**
     * Endpoint for updating user information.
     * @param username the new username to update.
     * @param userid the UUID of the user to update.
     * @return a ResponseEntity with a success message if the user is updated successfully, or an error message otherwise.
     */
    @PutMapping("/user/update")
    public ResponseEntity<String> updateUser(@RequestParam String username,@RequestParam UUID userid) {
        try {
            userService.updateUser(username,userid); // Implement this method in your service layer
            return ResponseEntity.ok("User updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to update user: " + e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving all users with pagination.
     * @param page the page number of results to retrieve.
     * @param size the number of results per page.
     * @return a Page containing UserDTO objects.
     */
    @GetMapping("/user/get-all")
    public Page<UserDTO> findAllUsers( @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "50") int size)
    {
        if(page<0)
        {
            page=0;
        }
        if(size<1 || size>50)
        {
            size=50;
        }
        Pageable pageable = PageRequest.of(page, size);
        return userService.findAll(pageable);
    }
}
