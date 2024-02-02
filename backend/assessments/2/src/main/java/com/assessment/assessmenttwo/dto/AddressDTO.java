package com.assessment.assessmenttwo.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

//import java.util.int;
@Entity
@Table(name="address")
@Data
@RequiredArgsConstructor
public class AddressDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String nickName;
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    private UserDTO user;

}
