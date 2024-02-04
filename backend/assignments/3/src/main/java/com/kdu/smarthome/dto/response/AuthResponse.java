package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Represents a response for authentication operations.
 */
@Data
@AllArgsConstructor
public class AuthResponse {

    /**
     * A message associated with the authentication response.
     */
    String message;

    /**
     * The authentication token.
     */
    String token;

    /**
     * The HTTP status of the response.
     */
    HttpStatus httpStatus;
}
