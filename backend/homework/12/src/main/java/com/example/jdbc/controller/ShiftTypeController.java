/**
 * The ShiftTypeController class handles HTTP requests related to shift types.
 * It defines endpoints for adding shift types and retrieving shift types by tenant.
 */
package com.example.jdbc.controller;

import com.example.jdbc.dto.ShiftTypeDTO;
import com.example.jdbc.service.ShiftTypeService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class ShiftTypeController {

    private final ShiftTypeService shiftTypeService;

    /**
     * Constructor for ShiftTypeController class.
     * @param service the ShiftTypeService instance to handle business logic related to shift types.
     */
    @Autowired
    public ShiftTypeController(ShiftTypeService service) {
        this.shiftTypeService = service;
    }

    /**
     * Endpoint for adding a new shift type.
     * @param shiftTypeRequest the ShiftTypeDTO object containing information about the shift type to be added.
     * @return a ResponseEntity with a success message if the shift type is added successfully, or an error message otherwise.
     */
    @PostMapping("/shifttype/add")
    public ResponseEntity<String> addShiftType(@RequestBody ShiftTypeDTO shiftTypeRequest) {
        try {
            log.info(shiftTypeRequest.toString());
            if (shiftTypeService.addShiftType(shiftTypeRequest))
                return ResponseEntity.ok("Successfully added");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to add shift type");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to add shift type");
    }

    /**
     * Endpoint for retrieving shift types by tenant ID.
     * @param tenantID the UUID of the tenant whose shift types are to be retrieved.
     * @return a ResponseEntity with a list of ShiftTypeDTO objects if shift types are found for the given tenant,
     *         or an error message otherwise.
     */
    @GetMapping("shifttype/getbytenant")
    public ResponseEntity<String> getByTenant(@RequestParam @JsonDeserialize UUID tenantID) {
        try {
            List<ShiftTypeDTO> shiftTypeDTOList = shiftTypeService.getShift(tenantID);
            if (shiftTypeDTOList != null) {
                log.info("Shift type list received.");
                return ResponseEntity.ok(shiftTypeDTOList.toString());
            }
            throw new NullPointerException();
        } catch (Exception e) {
            log.error("Exception at getByTenant:" + e);
            throw e;
        }
    }
}
