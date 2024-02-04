package com.kdu.smarthome.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for handling exceptions across the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles the UsernameNotFoundException and returns a ResponseEntity with a BAD_REQUEST status and the exception message.
     *
     * @param ex The UsernameNotFoundException to handle
     * @return ResponseEntity containing the exception message and BAD_REQUEST status
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
