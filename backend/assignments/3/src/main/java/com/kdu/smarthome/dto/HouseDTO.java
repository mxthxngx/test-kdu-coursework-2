package com.kdu.smarthome.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Represents a DTO (Data Transfer Object) for a house.
 */
@Data
@RequiredArgsConstructor
public class HouseDTO {
    /**
     * The address of the house.
     */
    String address;

    /**
     * The name of the house.
     */
    String house_name;
}
