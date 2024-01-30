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

    public boolean addShift(ShiftDTO shiftDTO) {
        try {

        if(shiftDAO.addShift(shiftDTO)!=0)
        {
            return true;
        }
            return false;
        }
        catch (Exception e)
        {
            log.error(e.toString());
            return false;
        }
    }
    public List<ShiftDTO> getShift(UUID tenantID)
    {
      return  shiftDAO.getShift(tenantID);
    }
}
