package com.example.jdbc.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AllDTO {

    TenantDTO tenantDTO;
    ShiftUserDTO shiftUserDTO;
    ShiftDTO shiftDTO;
    ShiftTypeDTO shiftTypeDTO;
    UserDTO user;

}
