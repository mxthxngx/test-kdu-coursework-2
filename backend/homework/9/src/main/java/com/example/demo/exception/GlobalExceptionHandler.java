package com.example.demo.exception;

import com.example.demo.dto.ErrorDTO;
import com.example.demo.exception.custom.DataTypeException;
import com.example.demo.exception.custom.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.demo.exception.custom.BadRequestException;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value={ResourceNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleCustomException(ResourceNotFoundException ex)
    {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<ErrorDTO> handleBadRequestException(BadRequestException ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value={DataTypeException.class})
    public ResponseEntity<ErrorDTO> handleNullException(ResourceNotFoundException ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    }

