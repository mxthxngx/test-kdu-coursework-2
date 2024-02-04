package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.RoomDTO;
import com.kdu.smarthome.dto.response.RoomResponse;
import com.kdu.smarthome.model.RoomModel;
import com.kdu.smarthome.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing room-related operations.
 */
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Endpoint for adding a room to a house.
     *
     * @param houseId  the ID of the house to which the room will be added
     * @param roomDTO  the DTO containing the details of the room to be added
     * @return ResponseEntity containing the response to the room addition
     */
    @PostMapping
    public ResponseEntity<RoomResponse> addRoomToHouse(
            @RequestParam("houseId") Integer houseId,
            @RequestBody RoomDTO roomDTO) {

        if (houseId == null) {
            RoomResponse response = new RoomResponse("HouseId cannot be empty", null, HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (roomDTO == null || roomDTO.getRoom_name() == null || roomDTO.getRoom_name().isEmpty()) {
            RoomResponse response = new RoomResponse("Room name cannot be empty", null, HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            RoomModel roomModel = roomService.addRoom(roomDTO, houseId);
            return ResponseEntity.status(HttpStatus.OK).body(new RoomResponse("Room added successfully", roomModel, HttpStatus.OK));
        } catch (Exception e) {
            if (e.getMessage().equals("Current user isn't admin")) {
                RoomResponse response = new RoomResponse("Current user isn't admin", null, HttpStatus.UNAUTHORIZED);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            } else {
                RoomResponse response = new RoomResponse("Failed to add room to the house: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }
    }
}
