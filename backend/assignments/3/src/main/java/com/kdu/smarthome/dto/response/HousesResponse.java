package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Represents a response for houses operations.
 */
@Data
@AllArgsConstructor
public class HousesResponse {

    /**
     * A message associated with the response.
     */
    String message;

    /**
     * A string representation of houses data.
     */
    String houses;

    /**
     * The HTTP status of the response.
     */
    HttpStatus httpStatus;
}
