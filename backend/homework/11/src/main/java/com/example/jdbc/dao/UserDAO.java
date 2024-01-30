package com.example.jdbc.dao;

import com.example.jdbc.dto.ShiftDTO;
import com.example.jdbc.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Slf4j
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addUser(UserDTO user) {
        try {
            String sqlStatement = "INSERT INTO users (username, loggedin, time_zone, tenant_id) VALUES (?, ?, ?, ?)";
            return jdbcTemplate.update(sqlStatement, user.getUsername(), user.getLoggedIn(), user.getTimeZone(), user.getTenantId());
        } catch (Exception e) {
            log.error("An exception occurred while adding user: {}", e.getMessage());
            throw e;
        }
    }
    public List<UserDTO> getUsersByTenant(UUID tenantID)
    {
        try {
            String sqlStatement = "SELECT * FROM users where tenant_id = ?";

            return jdbcTemplate.query(sqlStatement, new Object[]{tenantID},(rs,rowNum)->
                    new UserDTO(
                             rs.getString("username"),
                            rs.getString("time_zone"),
                            (UUID) rs.getObject("tenant_id"))
            );
        } catch (Exception e) {

            log.error("An exception had occurred in getuserbyid: " + e);
            throw e;
        }
    }
    public void updateUser(String username,UUID userID)
    {
        try {
            String sqlStatement = "UPDATE users SET username = ? WHERE id = ?";
           jdbcTemplate.update(sqlStatement,username,userID);
           log.info("Successful updation");
        }
        catch (Exception e)
        {
            log.error("Exception in update User"+e);
            throw e;

        }
        }
}
