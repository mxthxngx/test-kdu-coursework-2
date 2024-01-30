package com.example.jdbc.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class ShiftTypeDTO {
    private String uniqueName;
    private String description;
    @JsonDeserialize
    private UUID tenantID;

    public ShiftTypeDTO(String uqName, String description) {

        this.uniqueName=uqName;
        this.description=description;
    }
}
