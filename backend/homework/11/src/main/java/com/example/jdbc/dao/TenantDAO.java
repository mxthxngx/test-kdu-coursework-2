package com.example.jdbc.dao;
import com.example.jdbc.dto.TenantDTO;
import com.example.jdbc.model.Tenant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@Repository
@Slf4j
public class TenantDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TenantDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Adds a new tenant to the database.
     *
     * @param  tenant  the TenantDTO object representing the tenant to be added
     * @return         the number of rows affected in the database
     */
    public int addTenant(TenantDTO tenant) {
        try {
            String sqlStatement = "INSERT INTO tenant (name) VALUES (?)";
            return jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sqlStatement);
                ps.setString(1, tenant.getTenantName());
                return ps;
            });
        } catch (Exception e) {

            log.error("An exception had occurred in addTenant: " + e);
            throw e;
        }
    }
}
