/**
 * The ShiftDTO class represents a data transfer object for shift-related information.
 */
package com.example.jdbc.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "shifts")
public class ShiftDTO {

    /**
     * The unique identifier for the shift.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The ShiftTypeDTO representing the type of shift associated with this shift.
     */
    @ManyToOne
    @JoinColumn(name = "shift_type_id")
    private ShiftTypeDTO shiftType;

    /**
     * The name of the shift.
     */
    @Column(name = "name", length = 128)
    private String name;

    /**
     * The start date of the shift.
     */
    @Column(name = "date_start")
    private Date dateStart;

    /**
     * The end date of the shift.
     */
    @Column(name = "date_end")
    private Date dateEnd;

    /**
     * The start time of the shift.
     */
    @Column(name = "time_start")
    private Time timeStart;

    /**
     * The end time of the shift.
     */
    @Column(name = "time_end")
    private Time timeEnd;

    /**
     * The time zone associated with the shift.
     */
    @Column(name = "time_zone", length = 32)
    private String timeZone;

    /**
     * The TenantDTO representing the tenant associated with this shift.
     */
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private TenantDTO tenant;
}
