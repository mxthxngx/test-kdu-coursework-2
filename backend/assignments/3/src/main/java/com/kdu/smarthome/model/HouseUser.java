package com.kdu.smarthome.model;

import com.kdu.smarthome.dto.UserDTO;
import com.kdu.smarthome.model.HouseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Point of this class is to maintain a table that has the houseID and userID to first verify is the person is a part of the house
 */

@Entity
@Table(name = "house_user",indexes = @Index(name = "idx_house_id", columnList = "house_id"))
@Data
public class HouseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "house_id")
    private HouseModel house;

    @ManyToOne
    @JoinColumn(name = "username")
    private UserDTO user;


    @Column(name="house_role")
    @Enumerated(EnumType.STRING)
    HouseRole houseRole;

}
