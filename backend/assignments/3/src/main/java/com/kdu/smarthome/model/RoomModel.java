package com.kdu.smarthome.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity class representing a room in a smart home system.
 */
@Data
@Entity
@Table(name="rooms")
public class RoomModel {
    /**
     * The unique identifier for the room.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * The name of the room.
     */
    String roomName;

    /**
     * The house to which the room belongs.
     */
    @ManyToOne
    @JoinColumn(name="house_id")
    HouseModel houseModel;
}
