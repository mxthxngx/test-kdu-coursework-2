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

/**
 * The ShiftTypeService class provides service methods for managing shift types.
 * It interacts with the ShiftTypeDAO to perform database operations related to shift types.
 */
@Slf4j
@Service
public class ShiftTypeService {

    private final ShiftTypeDAO shiftTypeDAO;

    /**
     * Constructs a new ShiftTypeService instance.
     * @param shiftTypeDAO The data access object for shift types.
     */
    @Autowired
    public ShiftTypeService(ShiftTypeDAO shiftTypeDAO) {
        this.shiftTypeDAO = shiftTypeDAO;
    }

    /**
     * Adds a new shift type to the database.
     * @param shiftTypeDTO The shift type DTO to be added.
     * @return true if the shift type is successfully added, otherwise false.
     * @throws InvalidPropertyException if the shift type's unique name is invalid.
     */
    public boolean addShiftType(ShiftTypeDTO shiftTypeDTO) {
        try {
            if (!(shiftTypeDTO.getUniqueName() == ShiftTypeDTO.UniqueEnum.MORNING ||
                    shiftTypeDTO.getUniqueName() == ShiftTypeDTO.UniqueEnum.AFTERNOON ||
                    shiftTypeDTO.getUniqueName() == ShiftTypeDTO.UniqueEnum.NIGHT)) {
                throw new InvalidPropertyException(ShiftDTO.class, "uniqueName", "Invalid shift unique name - should be MORNING, AFTERNOON, or NIGHT");
            }

            log.info(shiftTypeDAO.save(shiftTypeDTO).toString());
            return true;
        } catch (Exception e) {
            log.error("Error at addshiftype: " + e);
            throw e;
        }
    }

    /**
     * Retrieves a list of shift types for a specific tenant.
     * @param tenantID The unique identifier of the tenant.
     * @return A list of shift types associated with the specified tenant.
     */
    public List<ShiftTypeDTO> getShift(UUID tenantID) {
        return shiftTypeDAO.findByTenantId(tenantID);
    }
}
