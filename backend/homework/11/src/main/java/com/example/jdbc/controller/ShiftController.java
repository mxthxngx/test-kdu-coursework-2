package com.example.jdbc.controller;

import com.example.jdbc.dto.ShiftDTO;
import com.example.jdbc.service.ShiftService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ShiftController {

    private final ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    /**
     * Adds a shift using the provided ShiftDTO.
     *
     * @param  shiftRequest   the ShiftDTO to be added
     * @return                a ResponseEntity containing a success or error message
     */
    @PostMapping("/shift/add")
    public ResponseEntity<String> addShift(@RequestBody ShiftDTO shiftRequest) {
        try {
            boolean result = shiftService.addShift(shiftRequest);
            if (result) {
                return ResponseEntity.ok("Shift added successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add shift");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    /**
     * Retrieves shift information for a specific tenant.
     *
     * @param  tenantID  the UUID of the tenant
     * @return           a ResponseEntity containing the shift information, or an error message
     */
    @GetMapping("shift/getbytenant")
    public ResponseEntity<String> getByTenant(@RequestParam @JsonDeserialize UUID tenantID) {
        try {
            List<ShiftDTO> shiftDTO = shiftService.getShift(tenantID);
            if (shiftDTO!=null) {
                return ResponseEntity.ok(shiftDTO.toString());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get shift");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
