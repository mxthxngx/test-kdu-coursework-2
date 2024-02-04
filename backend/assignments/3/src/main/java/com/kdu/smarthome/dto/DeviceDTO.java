package com.kdu.smarthome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a DTO (Data Transfer Object) for a device.
 */
@Data
@AllArgsConstructor
public class DeviceDTO {
    /**
     * The kickston ID of the device.
     */
    private String kickston_id;

    /**
     * The username associated with the device.
     */
    private String device_username;

    /**
     * The password associated with the device.
     */
    private String device_password;

    /**
     * The manufacture date and time of the device.
     */
    private String manufacture_date_time;

    /**
     * The manufacture factory place of the device.
     */
    private String manufacture_factory_place;
}
