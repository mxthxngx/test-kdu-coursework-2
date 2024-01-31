package com.example.jdbc.service;

import com.example.jdbc.dao.ShiftDAO;
import com.example.jdbc.dto.ShiftDTO;
import java.sql.Timestamp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

/**
 * The ShiftService class provides business logic operations related to shifts.
 */
@Slf4j
@Service
public class ShiftService {
    private final ShiftDAO shiftDAO;

    @Autowired
    public ShiftService(ShiftDAO shiftDAO) {
        this.shiftDAO = shiftDAO;
    }

    /**
     * Adds a shift using the provided ShiftDTO.
     *
     * @param  shiftDTO  the ShiftDTO to be added
     * @return           true if the shift is added successfully, false otherwise
     */
    public boolean addShift(ShiftDTO shiftDTO) {
        try {
            log.info(shiftDAO.save(shiftDTO).toString());
            return true;
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
    }

    /**
     * Retrieves a list of ShiftDTO objects for the given tenant ID.
     *
     * @param  tenantID  the ID of the tenant
     * @return           a list of ShiftDTO objects
     */
    public List<ShiftDTO> getShift(UUID tenantID) {
        return shiftDAO.findByTenantId(tenantID);
    }

    /**
     * Find the top 3 shifts within the given date range.
     *
     * @param  startDate  the start date of the date range
     * @param  endDate    the end date of the date range
     * @return            a list of ShiftDTO objects representing the top 3 shifts
     */
    public List<ShiftDTO> findTop3Shifts(Timestamp startDate, Timestamp endDate) {
        return shiftDAO.findTop3ByDateRange(startDate, endDate);
    }
}
