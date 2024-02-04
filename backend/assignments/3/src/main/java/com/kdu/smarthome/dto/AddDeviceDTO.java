package com.kdu.smarthome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a DTO (Data Transfer Object) for adding a device.
 */
@Data
@AllArgsConstructor
public class AddDeviceDTO {
    /**
     * The ID of the house where the device is being added.
     */
    private String houseId;

    /**
     * The ID of the room where the device is being added.
     */
    private String roomId;

    /**
     * The kickston ID of the device being added.
     */
    private String kickstonId;
}
