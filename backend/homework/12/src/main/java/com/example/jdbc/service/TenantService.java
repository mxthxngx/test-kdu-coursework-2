package com.example.jdbc.service;

import com.example.jdbc.dao.TenantDAO;
import com.example.jdbc.dto.TenantDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The TenantService class provides business logic operations related to tenants.
 */
@Service
@Slf4j
public class TenantService {
    private final TenantDAO tenantDAO;

    @Autowired
    public TenantService(TenantDAO tenantDAO) {
        this.tenantDAO = tenantDAO;
    }

    /**
     * Adds a tenant to the system.
     *
     * @param  tenantDTO  the TenantDTO object to be added
     * @return            true if the tenant is successfully added, false otherwise
     */
    public boolean addTenant(TenantDTO tenantDTO) {
        try {
            log.info(tenantDAO.save(tenantDTO).toString());
            return true;
        } catch (Exception e) {
            log.error("Error occurred while adding tenant: " + e.getMessage());
            return false;
        }
    }
}
