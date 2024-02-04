package com.kdu.smarthome.dto.response;

import com.kdu.smarthome.model.RoomModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Represents a response for room operations.
 */
@Data
@AllArgsConstructor
public class RoomResponse {
    /**
     * A message associated with the response.
     */
    String message;

    /**
     * The room model associated with the response.
     */
    RoomModel room = new RoomModel();

    /**
     * The HTTP status of the response.
     */
    HttpStatus httpStatus;
}
