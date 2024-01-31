/**
 * The ShiftUserController class handles HTTP requests related to shift users.
 * It defines endpoints for adding, retrieving, and deleting shift users.
 */
package com.example.jdbc.controller;

import com.example.jdbc.dto.ShiftUserDTO;
import com.example.jdbc.service.ShiftUserService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@Slf4j
public class ShiftUserController {

    private final ShiftUserService userService;

    /**
     * Constructor for ShiftUserController class.
     * @param userService the ShiftUserService instance to handle business logic related to shift users.
     */
    @Autowired
    public ShiftUserController(ShiftUserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint for adding a new shift user.
     * @param userDTO the ShiftUserDTO object containing information about the shift user to be added.
     * @return a ResponseEntity with a success message if the shift user is added successfully, or an error message otherwise.
     */
    @PostMapping("/shift-user/add")
    public ResponseEntity<String> addShiftUser(@RequestBody ShiftUserDTO userDTO) {
        try {
            userService.addShiftUser(userDTO);
            return ResponseEntity.ok("Shift user added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add shift user: " + e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving shift users by tenant ID.
     * @param tenantID the UUID of the tenant whose shift users are to be retrieved.
     * @return a ResponseEntity with a list of ShiftUserDTO objects if shift users are found for the given tenant,
     *         or an error message otherwise.
     */
    @GetMapping("shift-user/getbytenant")
    public ResponseEntity<String> getByTenant(@RequestParam @JsonDeserialize UUID tenantID) {
        try {
            List<ShiftUserDTO> shiftUserDTOList = userService.getShiftUsersByTenantID(tenantID);
            if (shiftUserDTOList != null) {
                log.info("Shift user list received.");
                return ResponseEntity.ok(shiftUserDTOList.toString());
            }
            throw new NullPointerException();
        } catch (Exception e) {
            log.error("Exception at getByTenant:" + e);
            throw e;
        }
    }

    /**
     * Endpoint for deleting a shift user by ID.
     * @param shiftUserId the UUID of the shift user to be deleted.
     * @return a ResponseEntity with a success message if the shift user is deleted successfully,
     *         or an error message otherwise.
     */
    @DeleteMapping("/shift-user/delete/{shiftUserId}")
    public ResponseEntity<String> deleteShiftUser(@PathVariable UUID shiftUserId) {
        try {
            userService.deleteShiftUser(shiftUserId);
            return ResponseEntity.ok("Shift user deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Shift user not found with ID: " + shiftUserId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete shift user: " + e.getMessage());
        }
    }
}
