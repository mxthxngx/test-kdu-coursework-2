package com.example.jdbc.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class ShiftUserDTO {

    @JsonDeserialize
        private UUID shiftId;
    @JsonDeserialize
        private UUID userId;
    @JsonDeserialize
        private UUID tenantId;

}
