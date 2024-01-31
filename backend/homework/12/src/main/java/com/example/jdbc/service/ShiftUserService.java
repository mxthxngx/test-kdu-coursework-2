package com.example.jdbc.service;

import com.example.jdbc.dao.ShiftUserDAO;
import com.example.jdbc.dto.ShiftUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;
import java.util.Optional;
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
     * Adds a ShiftUserDTO to the database and returns true if successful.
     *
     * @param  shiftUserDTO  the ShiftUserDTO to be added
     * @return               true if the ShiftUserDTO was added successfully
     */
    public boolean addShiftUser(ShiftUserDTO shiftUserDTO)
    {
        try {
        log.info(shiftUserDAO.save(shiftUserDTO).toString());
            return true;

    }
        catch (Exception e)
    {
        log.error(e.toString());
        throw e;
    }
    }
    /**
     * Retrieves a list of ShiftUserDTO objects based on the provided tenant ID.
     *
     * @param  tenantID  the UUID of the tenant
     * @return           a list of ShiftUserDTO objects
     */
    public List<ShiftUserDTO> getShiftUsersByTenantID(UUID tenantID) {
        try {
            return shiftUserDAO.findByTenantId(tenantID);
        } catch (Exception e) {
            log.error("Error getting users by tenant: " + e);
            throw e;
        }
    }
    /**
     * Deletes a shift user by their ID if their shift ends at 23:00 UTC.
     *
     * @param  shiftUserId  the ID of the shift user to be deleted
     * @throws NoSuchFieldException if the shift user is not found
     * @throws IllegalArgumentException if the shift user does not end at 23:00 UTC
     */
    public void deleteShiftUser(UUID shiftUserId) throws NoSuchFieldException {
        Optional<ShiftUserDTO> shiftUserOptional = shiftUserDAO.findById(shiftUserId);
        if (shiftUserOptional.isPresent()) {
            ShiftUserDTO shiftUser = shiftUserOptional.get();
            if (shiftUser.getShift().getTimeEnd().equals(Time.valueOf("23:00:00"))) {
                shiftUserDAO.delete(shiftUser);
            } else {
                throw new IllegalArgumentException("Shift user does not end at 23:00 UTC");
            }
        } else {
            throw new NoSuchFieldException("Shift user not found with ID: " + shiftUserId);
        }
    }

}
