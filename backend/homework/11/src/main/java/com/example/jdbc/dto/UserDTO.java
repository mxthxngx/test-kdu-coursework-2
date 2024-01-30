package com.example.jdbc.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
@Data
@RequiredArgsConstructor
public class UserDTO {

    private String username;
    private int loggedIn;
    private String timeZone;
    @JsonDeserialize
    private UUID tenantId;

    public UserDTO(String username, String timeZone, UUID tenantId) {
        this.username=username;
        this.timeZone=timeZone;
        this.tenantId=tenantId;
    }
}
