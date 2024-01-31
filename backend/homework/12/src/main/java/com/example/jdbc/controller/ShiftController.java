package com.example.jdbc.controller;

import com.example.jdbc.dto.ShiftDTO;
import com.example.jdbc.service.ShiftService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;

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
 * Endpoint for adding a new shift.
 * 
 * @param shiftRequest the request object containing shift details
 * @return a response entity with a success or error message
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
     * Get shift by tenant ID.
     *
     * @param  tenantID   the UUID of the tenant
     * @return            the ResponseEntity containing the shift information
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
    /**
     * Retrieves the top 3 shifts within a specified time period.
     *
     * @return          a list of ShiftDTO objects representing the top 3 shifts
     */
    @GetMapping("shift/top3")
    public List<ShiftDTO> getTop3Shifts() {
        Timestamp startDate = Timestamp.valueOf("2023-01-01 00:00:00");
        Timestamp endDate = Timestamp.valueOf("2023-01-25 23:59:59");

        // Call the service method with Timestamp parameters
        return shiftService.findTop3Shifts(startDate, endDate);
    }
}
