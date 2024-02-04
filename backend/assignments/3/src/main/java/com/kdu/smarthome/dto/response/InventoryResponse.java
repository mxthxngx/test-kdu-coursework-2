package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Represents a response for inventory operations.
 */
@Data
@AllArgsConstructor
public class InventoryResponse {

        /**
         * A message associated with the response.
         */
        String message;

        /**
         * A string representation of inventory data.
         */
        String inventory;

        /**
         * The HTTP status of the response.
         */
        HttpStatus httpStatus;
}
