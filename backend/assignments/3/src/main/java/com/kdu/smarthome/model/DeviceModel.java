package com.kdu.smarthome.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entity class representing a device in a smart home system.
 */
@Data
@Entity
@Table(name = "device")
public class DeviceModel {
    /**
     * The unique identifier for the device.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The unique kickston ID associated with the device.
     */
    @Column(name = "kickston_id", unique = true)
    private String kickstonId;

    /**
     * The username associated with the device.
     */
    @Column(name = "device_username")
    private String deviceUsername;

    /**
     * The password associated with the device.
     */
    @Column(name = "device_password")
    private String devicePassword;

    /**
     * The date and time of manufacture for the device.
     */
    @Column(name = "manufacture_date_time")
    private LocalDateTime manufactureDateTime;

    /**
     * The place of manufacture for the device.
     */
    @Column(name = "manufacture_factory_place")
    private String manufactureFactoryPlace;
}
