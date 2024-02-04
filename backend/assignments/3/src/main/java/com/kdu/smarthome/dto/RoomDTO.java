package com.kdu.smarthome.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Represents a DTO (Data Transfer Object) for a room.
 */
@Data
@RequiredArgsConstructor
public class RoomDTO {
    /**
     * The name of the room.
     */
    String room_name;
}
