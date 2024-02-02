package com.assessment.assessmenttwo.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="users")
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String username;

    private String password;

    private String role;

    private String fullName;
    private String email;

    @OneToMany(cascade = CascadeType.ALL,mappedBy="user")
    private Set<AddressDTO> addressDTOList;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    private ShoppingCartDTO shoppingCartDTO;

}
