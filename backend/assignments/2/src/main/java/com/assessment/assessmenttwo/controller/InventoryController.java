package com.assessment.assessmenttwo.controller;

import com.assessment.assessmenttwo.dto.InventoryDTO;
import com.assessment.assessmenttwo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class InventoryController {
    @Autowired
    InventoryService inventoryService;
    @PostMapping("/inventory/add")
    public String addInventory(@RequestBody InventoryDTO inventoryDTO)
    {
        try {
            inventoryService.save(inventoryDTO);
            return "inventory saved!";
        }
        catch (Exception e)
        {
            throw e;
        }
    }
    @PutMapping("/inventory/update")
    public String updateInventory(@PathVariable UUID UUID, @RequestBody InventoryDTO inventoryDTO)
    {try {
        inventoryService.update(UUID, inventoryDTO);
        return "Updated!";
    }
    catch (Exception e)
    {
        throw e;
    }
    }
    @PutMapping("/inventory/delete")
    public String updateInventory(@PathVariable UUID UUID)
    {
        try
        {
            inventoryService.delete(UUID);
            return "Success";
        }
        catch (Exception e)
        {
            throw e;
        }
    }

}
