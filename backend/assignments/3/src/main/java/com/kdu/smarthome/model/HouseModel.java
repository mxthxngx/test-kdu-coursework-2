package com.kdu.smarthome.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;

/**
 * Entity class representing a house in a smart home system.
 */
@Data
@Entity
@Scope("prototype")
@Table(name="house")
@RequiredArgsConstructor
public class HouseModel {
    /**
     * The unique identifier for the house.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * The name of the house.
     */
    String houseName;

    /**
     * The address of the house.
     */
    String address;
}
