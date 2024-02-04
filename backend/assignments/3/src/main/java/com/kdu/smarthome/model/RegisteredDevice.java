package com.kdu.smarthome.model;

import jakarta.persistence.*;
import com.kdu.smarthome.dto.UserDTO;
import lombok.Data;

/**
 * Entity class representing a registered device associated with a user in a smart home system.
 */
@Data
@Entity
@Table(name="registered_device")
public class RegisteredDevice {
    /**
     * The unique identifier for the registered device.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user associated with the registered device.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserDTO userDTO;

    /**
     * The device model associated with the registered device.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    private DeviceModel deviceModel;
}
