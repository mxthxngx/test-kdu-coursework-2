package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.AddDeviceDTO;
import com.kdu.smarthome.dto.DeviceRegisterDTO;
import com.kdu.smarthome.dto.response.GenericResponse;
import com.kdu.smarthome.model.DeviceModel;
import com.kdu.smarthome.model.RegisteredDevice;
import com.kdu.smarthome.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.CredentialException;
import javax.validation.ValidationException;

/**
 * Controller class for managing device-related operations.
 */
@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;

    /**
     * Constructor for DeviceController.
     *
     * @param deviceService the DeviceService to be used
     */
    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /**
     * Endpoint for registering a new device.
     *
     * @param deviceRegisterDTO the DTO containing device registration details
     * @return ResponseEntity containing registration response
     */
    @PostMapping("/register")
    public ResponseEntity<GenericResponse> addDevice(@RequestBody DeviceRegisterDTO deviceRegisterDTO) {
        try {
            RegisteredDevice registeredDevice = deviceService.register(deviceRegisterDTO);
            return ResponseEntity.ok(new GenericResponse("Device registered successfully", registeredDevice, HttpStatus.OK));
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(new GenericResponse("Validation failed: " + e.getMessage(), null, HttpStatus.BAD_REQUEST));
        } catch (CredentialException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Invalid credentials", null, HttpStatus.UNAUTHORIZED));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse("Unexpected error occurred: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    /**
     * Endpoint for adding a device to a house.
     *
     * @param addDeviceRequest the DTO containing details of the device to be added to a house
     * @return ResponseEntity containing response of adding device to a house
     */
    @PostMapping("/add")
    public ResponseEntity<GenericResponse> addDeviceToHouse(@RequestBody AddDeviceDTO addDeviceRequest) {
        try {
            DeviceModel deviceModel = deviceService.addDevice(addDeviceRequest);
            return ResponseEntity.ok(new GenericResponse("Device added to house successfully", deviceModel, HttpStatus.OK));
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(new GenericResponse("Validation failed: " + e.getMessage(), null, HttpStatus.BAD_REQUEST));
        } catch (RuntimeException e) {
            HttpStatus status;
            if (e.getMessage().contains("House ID not found") || e.getMessage().contains("Room ID not found") || e.getMessage().contains("Device ID not found")) {
                status = HttpStatus.BAD_REQUEST;
            } else {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            return ResponseEntity.status(status).body(new GenericResponse("Unexpected error: " + e.getMessage(), null, status));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse("Unexpected error: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
