package com.example.springbootapidemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserEntity {

    private String username;

    private String password;

    private String role;
}
