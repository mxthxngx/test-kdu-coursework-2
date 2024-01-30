package com.example.jdbc.dao;

import com.example.jdbc.dto.AllDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class AllDAO {

    private final ShiftDAO shiftDAO;
    private final ShiftTypeDAO shiftTypeDAO;
    private final ShiftUserDAO shiftUserDAO;
    private final UserDAO userDAO;
    private final TenantDAO tenantDAO;

    @Autowired
    public AllDAO( ShiftDAO shiftDAO, ShiftTypeDAO shiftTypeDAO, ShiftUserDAO shiftUserDAO, UserDAO userDAO, TenantDAO tenantDAO) {
        this.shiftDAO = shiftDAO;
        this.shiftTypeDAO = shiftTypeDAO;
        this.shiftUserDAO = shiftUserDAO;
        this.userDAO = userDAO;
        this.tenantDAO = tenantDAO;
    }

    public int addAllDAO(AllDTO allDTO)
    {
        try
        {
            log.info("add shift user: "+shiftUserDAO.addShiftUser(allDTO.getShiftUserDTO()));
            log.info("add shift type: "+shiftTypeDAO.addShiftType(allDTO.getShiftTypeDTO()));
            log.info("add shift type: "+userDAO.addUser(allDTO.getUser()));
            log.info("add shift: "+shiftDAO.addShift(allDTO.getShiftDTO()));
            log.info("add tenant type: "+tenantDAO.addTenant(allDTO.getTenantDTO()));
            return 1;
        }
        catch (Exception e)
        {
            log.error(e.toString());
            throw e;
        }
    }

}
