package com.caching.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorDTO {
    String errorMessage;
    HttpStatus statusCode;
}