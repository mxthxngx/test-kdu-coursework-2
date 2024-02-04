package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Represents a generic response for various operations.
 */
@Data
@AllArgsConstructor
public class GenericResponse {

    /**
     * A message associated with the response.
     */
    String message;

    /**
     * An object representing data associated with the response.
     */
    Object object;

    /**
     * The HTTP status of the response.
     */
    HttpStatus httpStatus;
}
