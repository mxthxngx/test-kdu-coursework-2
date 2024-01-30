package com.example.jdbc.model;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class ShiftUser {
    private UUID id;
    private UUID shiftId;
    private UUID userId;
    private UUID tenantId;
}
