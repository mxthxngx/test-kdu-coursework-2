package com.example.jdbc.controller;

import com.example.jdbc.dto.ShiftTypeDTO;
import com.example.jdbc.dto.ShiftUserDTO;
import com.example.jdbc.service.ShiftUserService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class ShiftUserController {

    private final ShiftUserService userService;

    @Autowired
    public ShiftUserController(ShiftUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/shift-user/add")
    public ResponseEntity<String> addShiftUser(@RequestBody ShiftUserDTO userDTO) {
        try {
            userService.addShiftUser(userDTO);
            return ResponseEntity.ok("Shift user added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add shift user: " + e.getMessage());
        }
    }
    @GetMapping("shift-user/getbytenant")
    public ResponseEntity<String> getByTenant(@RequestParam @JsonDeserialize UUID tenantID)
    {
        try
        {
            List<ShiftUserDTO> shiftTypeDTOList = userService.getShiftUsersByTenantID(tenantID);
            if(shiftTypeDTOList!=null)
            {
                log.info("Shift type list received.");
                return ResponseEntity.ok(shiftTypeDTOList.toString());
            }
            throw new NullPointerException();
        }
        catch (Exception e)
        {
            log.error("Exception at getByTenant:"+e);
            throw e;
        }
    }

}
