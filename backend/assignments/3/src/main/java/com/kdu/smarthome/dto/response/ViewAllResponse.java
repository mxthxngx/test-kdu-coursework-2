package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Represents a response for viewing all rooms and devices.
 */
@Data
@AllArgsConstructor
public class ViewAllResponse {

    /**
     * A message associated with the response.
     */
    String message;

    /**
     * A string representation of rooms and devices data.
     */
    String roomsAndDevices;

    /**
     * The HTTP status of the response.
     */
    HttpStatus httpStatus;
}
