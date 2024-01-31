/**
 * This class represents a Data Transfer Object (DTO) for a tenant entity.
 * It contains fields and methods to interact with tenant data in the database.
 */
package com.example.jdbc.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * The TenantDTO class represents a tenant entity in the database.
 * It is annotated with JPA annotations to map it to the corresponding database table.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "tenant")
public class TenantDTO {

    /**
     * The unique identifier for the tenant.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    /**
     * The name of the tenant.
     */
    @Column(name = "name", length = 255)
    private String tenantName;

    /**
     * Constructs a new TenantDTO instance.
     * @param id The unique identifier for the tenant.
     * @param tenantName The name of the tenant.
     */
    public TenantDTO(UUID id, String tenantName) {
        this.id = id;
        this.tenantName = tenantName;
    }
}
