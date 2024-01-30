package com.example.jdbc.dao;

import com.example.jdbc.dto.ShiftDTO;

import com.example.jdbc.model.Shift;
import com.example.jdbc.model.Tenant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
@Slf4j
public class ShiftDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ShiftDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addShift(ShiftDTO shift) {
        try {
            String sqlStatement = "INSERT INTO shifts (shift_type_id, name, date_start, date_end, " +
                    "time_start, time_end,tenant_id) VALUES (?, ?, ?, ?, ?, ?,?)";

            return jdbcTemplate.update((connection) -> {
                PreparedStatement ps = connection.prepareStatement(sqlStatement);
                ps.setObject(1, shift.getShiftTypeId());
                ps.setString(2, shift.getName());
                ps.setDate(3, new java.sql.Date(shift.getDateStart().getTime()));
                ps.setDate(4, new java.sql.Date(shift.getDateEnd().getTime()));
                ps.setTime(5, shift.getTimeStart());
                ps.setTime(6, shift.getTimeEnd());
                ps.setObject(7, shift.getTenantId());
                return ps;
            });
        } catch (Exception e) {

            log.error("An exception had occurred in addShift: " + e);
            throw e;
        }
    }
    public List<ShiftDTO> getShift(UUID tenantID)
    {
        try {
            String sqlStatement = "SELECT * FROM shifts where tenant_id = ?";

          return jdbcTemplate.query(sqlStatement, new Object[]{tenantID},(rs,rowNum)->
                  new ShiftDTO(
                          (UUID) rs.getObject("shift_type_id"),
                          rs.getString("name"),
                          rs.getDate("date_start"),
                          rs.getDate("date_end"),
                          rs.getTime("time_start"),
                          rs.getTime("time_end"),
                          rs.getString("time_zone"),
                          (UUID) rs.getObject("tenant_id"))
                  );
        } catch (Exception e) {

            log.error("An exception had occurred in addShift: " + e);
            throw e;
        }
    }
}
