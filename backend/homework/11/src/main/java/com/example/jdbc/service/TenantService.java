package com.example.jdbc.service;

import com.example.jdbc.dao.TenantDAO;
import com.example.jdbc.dto.TenantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantService {
    TenantDAO tenantDAO;
    @Autowired
    TenantService(TenantDAO tenantDAO)
    {
        this.tenantDAO=tenantDAO;
    }

    /**
     * Adds a new tenant using the provided TenantDTO.
     *
     * @param  tenantDTO  the TenantDTO object representing the new tenant
     * @return            true if the tenant was successfully added, false otherwise
     */
   
    public boolean addTenant(TenantDTO tenantDTO)
    {
        try
        {

            tenantDAO.addTenant(tenantDTO);
            return true;

         }
        catch (Exception e)
        {
            return false;
        }

    }
}
