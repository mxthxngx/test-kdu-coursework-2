package com.example.jdbc.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
@Data
@RequiredArgsConstructor
public class User {
    private UUID id;
    private String username;
    private int loggedIn;
    private String timeZone;
    private UUID tenantId;
}
