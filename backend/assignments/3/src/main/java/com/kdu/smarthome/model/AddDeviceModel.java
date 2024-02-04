package com.kdu.smarthome.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Entity class representing the association between a device, a room, a house, and a house user in a smart home system.
 */
@Entity
@Data
@RequiredArgsConstructor
@Table(name="added_devices")
public class AddDeviceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "kickston_id")
    DeviceModel deviceModel;

    @ManyToOne
    @JoinColumn(name="room_id")
    RoomModel roomModel;

    @ManyToOne
    @JoinColumn(name="house_id")
    HouseModel houseModel;

    @ManyToOne
    @JoinColumn(name = "house_user_id")
    private HouseUser houseUser;
}
