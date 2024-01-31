/**
 * The AllDTO class represents a container for various DTO objects related to JDBC operations.
 * It holds instances of TenantDTO, ShiftUserDTO, ShiftDTO, ShiftTypeDTO, and UserDTO.
 */
package com.example.jdbc.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AllDTO {

    /**
     * The TenantDTO object representing tenant data.
     */
    TenantDTO tenantDTO;

    /**
     * The ShiftUserDTO object representing shift user data.
     */
    ShiftUserDTO shiftUserDTO;

    /**
     * The ShiftDTO object representing shift data.
     */
    ShiftDTO shiftDTO;

    /**
     * The ShiftTypeDTO object representing shift type data.
     */
    ShiftTypeDTO shiftTypeDTO;

    /**
     * The UserDTO object representing user data.
     */
    UserDTO user;
}
