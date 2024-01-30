package com.example.jdbc.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;
@Data
@RequiredArgsConstructor
public class ShiftDTO {
    @JsonDeserialize
    private UUID shiftTypeId;
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private Time timeStart;
    private Time timeEnd;
    private String timeZone;
    @JsonDeserialize
    private UUID tenantId;


    public ShiftDTO(UUID shiftTypeId, String name, java.sql.Date dateStart, java.sql.Date dateEnd, Time timeStart, Time timeEnd, String timeZone,UUID tenantId) {
        this.shiftTypeId=shiftTypeId;
        this.name=name;
        this.dateEnd=dateEnd;
        this.dateStart = dateStart;
        this.timeZone = timeZone;
        this.tenantId = tenantId;
        this.timeEnd=timeEnd;
        this.timeStart=timeStart;
    }
}

