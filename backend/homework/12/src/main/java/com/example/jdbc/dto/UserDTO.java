/**
 * This class represents a Data Transfer Object (DTO) for a user entity.
 * It contains fields and methods to interact with user data in the database.
 */
package com.example.jdbc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * The UserDTO class represents a user entity in the database.
 * It is annotated with JPA annotations to map it to the corresponding database table.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserDTO {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    /**
     * The username of the user.
     */
    @Column(name = "username", length = 32)
    private String username;

    /**
     * The status indicating whether the user is logged in.
     */
    @Column(name = "loggedin")
    private int loggedIn;

    /**
     * The time zone of the user.
     */
    @Column(name = "time_zone", length = 32)
    private String timeZone;

    /**
     * The tenant to which the user belongs.
     * Many users can belong to one tenant.
     * It is annotated with @ManyToOne to establish the many-to-one relationship with the TenantDTO class.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tenant_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TenantDTO tenant;

    /**
     * Constructs a new UserDTO instance.
     * @param username The username of the user.
     * @param timeZone The time zone of the user.
     * @param tenant The tenant to which the user belongs.
     */
    public UserDTO(String username, String timeZone, TenantDTO tenant) {
        this.username = username;
        this.timeZone = timeZone;
        this.tenant = tenant;
    }
}
