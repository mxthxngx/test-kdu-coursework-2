package com.assessment.assessmenttwo.controller;

import com.assessment.assessmenttwo.dto.InventoryDTO;
import com.assessment.assessmenttwo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import java.util.int;

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
    public String updateInventory(@PathVariable int id, @RequestBody InventoryDTO inventoryDTO)
    {try {
        inventoryService.update(id, inventoryDTO);
        return "Updated!";
    }
    catch (Exception e)
    {
        throw e;
    }
    }
    @PutMapping("/inventory/delete")
    public String deleteInventory(@PathVariable int id)
    {
        try
        {
            inventoryService.delete(id);
            return "Success";
        }
        catch (Exception e)
        {
            throw e;
        }
    }

}
