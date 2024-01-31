package com.example.jdbc.dao;

import com.example.jdbc.dto.ShiftDTO;
import com.example.jdbc.dto.ShiftUserDTO;
import com.example.jdbc.model.ShiftUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;

@Repository
@Slf4j
public class ShiftUserDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ShiftUserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * A method to add a ShiftUserDTO to the database.
     *
     * @param  user  the ShiftUserDTO to be added
     * @return      the number of rows affected in the database
     */
    public int addShiftUser(ShiftUserDTO user)
    {
        try {
            String sqlStatement = "INSERT INTO shift_user (shift_id, user_id, tenant_id) VALUES ( ?, ?, ?)";
            return jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sqlStatement);
                ps.setObject(1, user.getShiftId());
                ps.setObject(2, user.getUserId());
                ps.setObject(3, user.getTenantId());
                return ps;
            });
        } catch (Exception e) {
            log.error("An exception occurred while adding shift user: " + e.getMessage());
            throw e;
        }
    }
    /**
     * Retrieves a list of ShiftUserDTO objects for the given tenant ID.
     *
     * @param  tenantID	the UUID of the tenant
     * @return         	a list of ShiftUserDTO objects
     */
    public List<ShiftUserDTO> getUsersByTenant(UUID tenantID)
    {
        try
        {
            String sqlStatement = "SELECT * FROM shift_user where tenant_id = ?";

            return jdbcTemplate.query(sqlStatement, new Object[]{tenantID},(rs,rowNum)->
                    new ShiftUserDTO((UUID) rs.getObject("shift_id"),
                            (UUID) rs.getObject("user_id"),
                            (UUID) rs.getObject("tenant_id")
                    )
            );
        }
        catch (Exception e) {

            log.error("An exception had occurred in addShift: " + e);
            throw e;
        }
    }
}
