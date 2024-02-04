package com.kdu.smarthome.dto;

import jakarta.persistence.*;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Represents a DTO (Data Transfer Object) for a user.
 */
@Data
@RequiredArgsConstructor
@Entity
@Table(name="users")
public class UserDTO {
    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * The username of the user (unique and not nullable).
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * The password of the user (not nullable).
     */
    @Column(nullable = false)
    private String password;

    /**
     * The role of the user.
     */
    private String role;

    /**
     * The name of the user.
     */
    private String name;

    /**
     * The first name of the user (not nullable).
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * The last name of the user (not nullable).
     */
    @Column(nullable = false)
    private String lastName;

    /**
     * The email address of the user.
     */
    private String email;
}
