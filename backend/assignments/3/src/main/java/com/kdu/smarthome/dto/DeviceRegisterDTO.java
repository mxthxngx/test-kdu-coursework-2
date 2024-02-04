package com.kdu.smarthome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a DTO (Data Transfer Object) for registering a device.
 */
@Data
@AllArgsConstructor
public class DeviceRegisterDTO {
    /**
     * The kickston ID of the device.
     */
    String kickston_id;

    /**
     * The username associated with the device.
     */
    String device_username;

    /**
     * The password associated with the device.
     */
    String device_password;
}
