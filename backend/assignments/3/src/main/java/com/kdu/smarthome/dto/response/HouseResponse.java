package com.kdu.smarthome.dto.response;

import com.kdu.smarthome.model.HouseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Represents a response for house operations.
 */
@Data
@AllArgsConstructor
public class HouseResponse {
    /**
     * A message associated with the response.
     */
    String message;

    /**
     * The house model associated with the response.
     */
    HouseModel house = new HouseModel();

    /**
     * The HTTP status of the response.
     */
    HttpStatus httpStatus;
}
