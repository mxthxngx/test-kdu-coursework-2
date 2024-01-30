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

    @Autowired
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping("/tenant/add")
    public ResponseEntity<String> addTenant(@RequestBody TenantDTO tenantDTO) {
        if (tenantService.addTenant(tenantDTO)) {
            return ResponseEntity.ok("Tenant added successfully");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to add tenant");
    }

}
