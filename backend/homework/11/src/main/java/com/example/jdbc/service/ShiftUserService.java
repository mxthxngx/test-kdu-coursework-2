package com.example.jdbc.service;

import com.example.jdbc.dao.ShiftUserDAO;
import com.example.jdbc.dto.ShiftUserDTO;
import com.example.jdbc.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
public class ShiftUserService {
    ShiftUserDAO shiftUserDAO;
    @Autowired
    ShiftUserService(ShiftUserDAO shiftUserDAO)
    {
        this.shiftUserDAO = shiftUserDAO;
    }
    /**
     * Adds a shift user using the provided ShiftUserDTO.
     *
     * @param  shiftUserDTO  the ShiftUserDTO to be added
     * @return              true if the shift user was added successfully
     */
    public boolean addShiftUser(ShiftUserDTO shiftUserDTO)
    {
        try {
        shiftUserDAO.addShiftUser(shiftUserDTO);
            return true;

    }
        catch (Exception e)
    {
        log.error(e.toString());
        throw e;
    }
    }
    /**
     * Retrieves a list of ShiftUserDTO objects based on the given tenant ID.
     *
     * @param  tenantID   the unique identifier of the tenant
     * @return            a list of ShiftUserDTO objects
     */
    public List<ShiftUserDTO> getShiftUsersByTenantID(UUID tenantID) {
        try {
            return shiftUserDAO.getUsersByTenant(tenantID);
        } catch (Exception e) {
            log.error("Error getting users by tenant: " + e);
            throw e;
        }
    }
}
