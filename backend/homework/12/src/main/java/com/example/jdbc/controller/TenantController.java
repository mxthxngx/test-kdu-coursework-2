/**
 * The TenantController class handles HTTP requests related to tenants.
 * It defines an endpoint for adding tenants.
 */
package com.example.jdbc.controller;

import com.example.jdbc.dto.TenantDTO;
import com.example.jdbc.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TenantController {

    private final TenantService tenantService;

    /**
     * Constructor for TenantController class.
     * @param tenantService the TenantService instance to handle business logic related to tenants.
     */
    @Autowired
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    /**
     * Endpoint for adding a new tenant.
     * @param tenantDTO the TenantDTO object containing information about the tenant to be added.
     * @return a ResponseEntity with a success message if the tenant is added successfully, or an error message otherwise.
     */
    @PostMapping("/tenant/add")
    public ResponseEntity<String> addTenant(@RequestBody TenantDTO tenantDTO) {
        if (tenantService.addTenant(tenantDTO)) {
            return ResponseEntity.ok("Tenant added successfully");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to add tenant");
    }

}
