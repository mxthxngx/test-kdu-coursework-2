package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.HouseDTO;
import com.kdu.smarthome.dto.UserNameDTO;
import com.kdu.smarthome.dto.response.GenericResponse;
import com.kdu.smarthome.dto.response.HousesResponse;
import com.kdu.smarthome.dto.response.HouseResponse;
import com.kdu.smarthome.dto.response.ViewAllResponse;
import com.kdu.smarthome.model.HouseModel;
import com.kdu.smarthome.model.HouseRole;
import com.kdu.smarthome.service.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller class for managing house-related operations.
 */
@RestController
@Slf4j
@RequestMapping("/api/v1/house")
public class HouseController {

    private final HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    /**
     * Endpoint for updating the address of a house.
     *
     * @param houseId     the ID of the house to update
     * @param requestBody the request body containing the new address
     * @return ResponseEntity containing the result of the update
     */
    @PutMapping("/")
    public ResponseEntity<GenericResponse> updateHouseAddress(
            @RequestParam Integer houseId,
            @RequestBody Map<String, String> requestBody) {

        String newAddress = requestBody.get("address");

        if (newAddress == null || newAddress.isEmpty()) {
            GenericResponse genericResponse = new GenericResponse("New address cannot be empty", null, HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericResponse);
        }

        try {
            HouseModel updatedHouse = houseService.updateHouseAddress(houseId, newAddress);
            GenericResponse genericResponse = new GenericResponse("House address updated successfully", updatedHouse, HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
        } catch (Exception e) {
            GenericResponse genericResponse = new GenericResponse("Failed to update house address: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genericResponse);
        }
    }

    /**
     * Endpoint for adding a new house.
     *
     * @param houseDTO the DTO containing the details of the new house
     * @return ResponseEntity containing the response to the house addition
     */
    @PostMapping
    public HttpEntity<HouseResponse> addHouse(@RequestBody HouseDTO houseDTO) {
        try {
            log.info("HOUSE DTO RECEIVED:" + houseDTO);
            HouseModel houseModel = houseService.addHouse(houseDTO, HouseRole.ADMIN);
            HouseResponse responseVal = new HouseResponse("House added!", houseModel, HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(responseVal);
        } catch (Exception e) {
            HouseResponse responseVal = new HouseResponse("Unable to Add House " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseVal);
        }
    }

    /**
     * Endpoint for retrieving all houses.
     *
     * @return ResponseEntity containing the response with the list of houses
     */
    @GetMapping("/")
    public ResponseEntity<HousesResponse> getAllHouses() {
        try {
            List<HouseModel> houseModelList = houseService.getAllHouses();
            HousesResponse genericResponse = new HousesResponse("Houses retrieved! ", houseModelList.toString(), HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
        } catch (Exception e) {
            HousesResponse genericResponse = new HousesResponse("Failed to get houses: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genericResponse);
        }
    }

    /**
     * Endpoint for adding a user to a house.
     *
     * @param houseId  the ID of the house to which the user is to be added
     * @param username the DTO containing the username of the user to be added
     * @return ResponseEntity containing the response to the user addition
     */
    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<GenericResponse> addUserToHouse(
            @PathVariable("houseId") Integer houseId,
            @RequestBody UserNameDTO username) {

        if (username == null || username.getUsername() == null || username.getUsername().isEmpty()) {
            GenericResponse genericResponse = new GenericResponse("Username cannot be empty", null, HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericResponse);
        }

        try {
            String userName = houseService.addUserToHouse(houseId, username.getUsername());
            GenericResponse genericResponse = new GenericResponse("User Successfully added : ", userName, HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(genericResponse);

        } catch (UsernameNotFoundException e) {
            GenericResponse genericResponse = new GenericResponse("Failed to add user to the house: Username not found", null, HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericResponse);

        } catch (Exception e) {
            if (e.getMessage().equals("User not admin")) {
                GenericResponse genericResponse = new GenericResponse("Failed to add user to the house: User is not an admin", null, HttpStatus.UNAUTHORIZED);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(genericResponse);
            } else {
                GenericResponse genericResponse = new GenericResponse("Failed to add user to the house: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genericResponse);
            }
        }
    }

    /**
     * Endpoint for retrieving all devices and rooms associated with a house.
     *
     * @param houseId the ID of the house
     * @return ResponseEntity containing the response with details of devices and rooms
     */
    @GetMapping("/{houseId}")
    public HttpEntity<ViewAllResponse> getAllDevicesAndRooms(@PathVariable String houseId) {
        try {
            Integer houseIDInt = Integer.parseInt(houseId);
            String addDeviceModel = houseService.getAllDetails(houseIDInt);
            ViewAllResponse viewAllResponse = new ViewAllResponse("Successfully retrieved", addDeviceModel, HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(viewAllResponse);

        } catch (Exception e) {
            ViewAllResponse viewAllResponse = new ViewAllResponse("Failed to get rooms and details to the house: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(viewAllResponse);

        }
    }
}
