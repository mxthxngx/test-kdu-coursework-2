package com.example.jdbc.controller;

import com.example.jdbc.dto.ShiftDTO;
import com.example.jdbc.dto.ShiftTypeDTO;
import com.example.jdbc.service.ShiftTypeService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class ShiftTypeController {
    private final ShiftTypeService shiftTypeService;

    @Autowired
    public ShiftTypeController(ShiftTypeService service) {
        this.shiftTypeService = service;
    }

    @PostMapping("/shifttype/add")
    public ResponseEntity<String> addShiftType(@RequestBody ShiftTypeDTO shiftTypeRequest) {
        try {
            log.info(shiftTypeRequest.toString());
            if(shiftTypeService.addShiftType(shiftTypeRequest))
            return ResponseEntity.ok("Successfully added");

            else  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to add shift type");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to add shift type");
        }
    }
    @GetMapping("shifttype/getbytenant")
    public ResponseEntity<String> getByTenant(@RequestParam @JsonDeserialize UUID tenantID)
    {
        try
        {
            List<ShiftTypeDTO> shiftTypeDTOList = shiftTypeService.getShift(tenantID);
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
