package com.example.jdbc.dao;

import com.example.jdbc.dto.ShiftDTO;
import com.example.jdbc.dto.ShiftTypeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Slf4j
public class ShiftTypeDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ShiftTypeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addShiftType(ShiftTypeDTO shiftType) {
        try {

            String sqlStatement = "INSERT INTO shift_types (id,uq_name, description,tenant_id) VALUES (?,?, ?,?)";
            return jdbcTemplate.update(sqlStatement, UUID.randomUUID(), shiftType.getUniqueName(), shiftType.getDescription(),shiftType.getTenantID());
        } catch (Exception e) {
            log.error("An exception had occurred in addShiftType: " + e);
            throw e;
        }
    }
    public List<ShiftTypeDTO> getShift(UUID tenantID)
    {
        try {
            String sqlStatement = "SELECT * FROM shift_types where tenant_id = ?";

            return jdbcTemplate.query(sqlStatement, new Object[]{tenantID},(rs,rowNum)->
                    new ShiftTypeDTO(

                            rs.getString("uq_name"),
                            rs.getString("description"))

            );
        } catch (Exception e) {

            log.error("An exception had occurred in addShift: " + e);
            throw e;
        }
    }
}
