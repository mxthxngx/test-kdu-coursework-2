package com.example.jdbc.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
@Data
@RequiredArgsConstructor
public class TenantDTO {
    @JsonDeserialize
    private UUID id;
    private String tenantName;
}
