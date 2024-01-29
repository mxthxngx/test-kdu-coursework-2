package com.example.springbootapidemo.exception;

import com.example.springbootapidemo.dto.ErrorDTO;
import com.example.springbootapidemo.exception.custom.MyCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles MyCustomException and returns an ErrorDTO with an appropriate message and HTTP status code.
     *
     * @param  ex   the MyCustomException to be handled
     * @return     an ErrorDTO containing the message and HTTP status code
     */
    @ExceptionHandler(value = {MyCustomException.class})
    public ResponseEntity<ErrorDTO> handleCustomException(MyCustomException ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage() + " [MyCustomException]", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    /**
     * Handles all kinds of exceptions and returns an ErrorDTO.
     *
     * @param  ex	the exception to be handled
     * @return      an ErrorDTO containing the exception message and HTTP status code
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDTO> allKindsOfException(Exception ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage() + " [Parent Exception]", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
