package com.example.jdbc.service;

import com.example.jdbc.dao.ShiftTypeDAO;
import com.example.jdbc.dto.ShiftDTO;
import com.example.jdbc.dto.ShiftTypeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Slf4j
@Service
public class ShiftTypeService {
    ShiftTypeDAO shiftTypeDAO;
    @Autowired
    ShiftTypeService(ShiftTypeDAO shiftTypeDAO)
    {
        this.shiftTypeDAO = shiftTypeDAO;
    }
    /**
     * Adds a shift type to the system.
     *
     * @param  shiftTypeDTO  the ShiftTypeDTO to be added
     * @return               true if the shift type was added successfully, false otherwise
     */
    public boolean addShiftType(ShiftTypeDTO shiftTypeDTO) {
        try {
            if (!(shiftTypeDTO.getUniqueName().equals("Morning") || shiftTypeDTO.getUniqueName().equals("Afternoon") || shiftTypeDTO.getUniqueName().equals("Night"))) {
                throw new InvalidPropertyException(ShiftDTO.class, "uniqueName", "Invalid shift unique name");
            }

            return shiftTypeDAO.addShiftType(shiftTypeDTO) != 0;
        } catch (Exception e) {
            log.error("Error at addshiftype: "+e);
           throw e;
        }
    }
    /**
     * Retrieves a list of ShiftTypeDTO objects for the specified tenant ID.
     *
     * @param  tenantID  the ID of the tenant
     * @return           a list of ShiftTypeDTO objects
     */
    public List<ShiftTypeDTO> getShift(UUID tenantID)
    {
        return shiftTypeDAO.getShift(tenantID);
    }
}
