package com.example.jdbc.service;

import com.example.jdbc.dao.ShiftDAO;
import com.example.jdbc.dto.ShiftDTO;
import com.example.jdbc.model.Shift;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Slf4j
@Service
public class ShiftService {
    ShiftDAO shiftDAO;

    @Autowired
    ShiftService(ShiftDAO shiftDAO) {
        this.shiftDAO = shiftDAO;
    }

    /**
     * Adds a ShiftDTO to the system.
     *
     * @param  shiftDTO   the ShiftDTO to be added
     * @return           true if the ShiftDTO is successfully added, false otherwise
     */
    public boolean addShift(ShiftDTO shiftDTO) {
        try {

        if(shiftDAO.addShift(shiftDTO)!=0)
            return true;


        }
        catch (Exception e)
        {
            log.error(e.toString());
            return false;
        }
        return false;
    }
    /**
     * Retrieves a list of ShiftDTO objects for the given tenant ID.
     *
     * @param  tenantID  the ID of the tenant
     * @return           a list of ShiftDTO objects
     */
    public List<ShiftDTO> getShift(UUID tenantID)
    {
      return  shiftDAO.getShift(tenantID);
    }
}
