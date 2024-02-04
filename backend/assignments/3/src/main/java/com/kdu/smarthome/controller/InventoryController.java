package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.DeviceDTO;
import com.kdu.smarthome.dto.response.GenericResponse;
import com.kdu.smarthome.dto.response.InventoryResponse;
import com.kdu.smarthome.model.DeviceModel;
import com.kdu.smarthome.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;

/**
 * Controller class for managing inventory-related operations.
 */
@RestController
@Slf4j
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Endpoint for adding inventory.
     *
     * @param deviceDTO the DTO containing the details of the device to be added to inventory
     * @return ResponseEntity containing the response to the inventory addition
     */
    @PostMapping
    public ResponseEntity<GenericResponse> addInventory(@RequestBody DeviceDTO deviceDTO) {
        try {
            log.info(deviceDTO.toString());
            DeviceModel deviceModel = inventoryService.add(deviceDTO);
            return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse("Inventory added successfully", deviceModel, HttpStatus.OK));
        } catch (ValidationException e) {
            log.error("Validation failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse("Validation failed: " + e.getMessage(), null, HttpStatus.BAD_REQUEST));
        } catch (Exception e) {
            log.error("Unexpected error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse("Unexpected error: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    /**
     * Endpoint for displaying inventory.
     *
     * @return ResponseEntity containing the response with the inventory list
     */
    @GetMapping
    public ResponseEntity<InventoryResponse> displayInventory() {
        try {
            String inventoryList = inventoryService.getAllDevices();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new InventoryResponse("Inventory retrieved successfully", inventoryList, HttpStatus.OK));
        } catch (Exception e) {
            log.error("Failed to retrieve inventory: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new InventoryResponse("Failed to retrieve inventory: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
