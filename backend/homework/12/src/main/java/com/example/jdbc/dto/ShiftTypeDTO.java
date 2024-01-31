/**
 * The ShiftTypeDTO class represents a data transfer object for shift type-related information.
 */
package com.example.jdbc.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "shift_types")
public class ShiftTypeDTO {

    /**
     * The unique identifier for the shift type.
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    /**
     * The unique name of the shift type.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "uq_name", nullable = false)
    @JsonDeserialize
    private UniqueEnum uniqueName;

    /**
     * The description of the shift type.
     */
    @Column(name = "description")
    private String description;

    /**
     * Indicates whether the shift type is active or not.
     */
    @Column(name = "active")
    private boolean active;

    /**
     * The date and time when the shift type was created.
     */
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    /**
     * The date and time when the shift type was last updated.
     */
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    /**
     * The time zone associated with the shift type.
     */
    @Column(name = "time_zone", length = 32, columnDefinition = "VARCHAR(32) DEFAULT 'UTC-2'")
    private String timeZone;

    /**
     * The tenant associated with this shift type.
     */
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private TenantDTO tenant;

    /**
     * Enum representing unique shift types.
     */
    public enum UniqueEnum implements Serializable {
        MORNING("Morning"), AFTERNOON("Afternoon"), NIGHT("Night");

        private final String uqName;

        UniqueEnum(String displayName) {
            this.uqName = displayName;
        }

        public String getDisplayName() {
            return uqName;
        }
    }
}
