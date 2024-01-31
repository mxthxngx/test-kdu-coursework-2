/**
 * The ShiftUserDTO class represents a data transfer object for shift user-related information.
 */
package com.example.jdbc.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "shift_user")
public class ShiftUserDTO {

    /**
     * The unique identifier for the shift user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The shift associated with the shift user.
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "shift_id")
    private ShiftDTO shift;

    /**
     * The user associated with the shift user.
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private UserDTO user;

    /**
     * The tenant associated with the shift user.
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tenant_id")
    private TenantDTO tenant;
}
