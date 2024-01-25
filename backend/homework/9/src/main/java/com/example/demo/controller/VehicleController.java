package com.example.demo.controller;
import com.example.demo.dto.VehicleDTO;
import com.example.demo.exception.custom.BadRequestException;
import com.example.demo.exception.custom.DataTypeException;
import com.example.demo.exception.custom.ResourceNotFoundException;
import com.example.demo.model.Vehicle;
import com.example.demo.services.FactoryService1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class VehicleController {

    private FactoryService1 factoryService1;
    @Autowired  VehicleController(FactoryService1 factoryService1)
    {
        this.factoryService1=factoryService1;
    }

    private List<Vehicle> vehiclesList = new ArrayList<>();

    @PostMapping("factory1/addvehicle")
    @Profile(value="prod")
    public ResponseEntity<String> addVehicle(@RequestBody @Valid VehicleDTO vehicle)
    {
        log.info("Request for adding vehicle executing ..");

        factoryService1.addVehicle(vehicle);
        return ResponseEntity.ok("Successfully Added!");
    }

    @GetMapping("/factory1/{id}")
    public ResponseEntity<?> showVehicle(@PathVariable String id) {
        try {
            if (id.chars().allMatch(Character::isDigit)) {
                Vehicle v = factoryService1.getVehicle(id);
                log.info("Vehicle shown...");

                if (v == null) {
                    throw new BadRequestException("Vehicle is null!");
                } else {
                    return ResponseEntity.ok(v);
                }
            } else {
                throw new DataTypeException("ID not an integer!");
            }
        } catch (DataTypeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data type: " + e.getMessage());
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }


    @PutMapping("/factory1/update/{id}")
    public ResponseEntity<String> updateFactory(@PathVariable String id, @RequestBody @Valid VehicleDTO vehicle) throws ResourceNotFoundException {
    log.info("Initiated update...");
        if( factoryService1.updateVehicle(vehicle,id)) {
        log.debug("Updated Vehicle successfully");
        return ResponseEntity.ok("Updated vehicle");
    }
    else
        throw new ResourceNotFoundException("ID not found!");
    }
    @DeleteMapping("/factory1/delete/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable String id) throws ResourceNotFoundException {
        log.info("Delete Vehicle initiated!");

        if (factoryService1.deleteVehicle(id)) {
            log.debug("Deletion successful");
            return ResponseEntity.ok("Deleted vehicle");
        } else {
            log.error("ID not found, throwing exception");
            throw new ResourceNotFoundException("ID not found!");
        }
    }


    @GetMapping("/factory1/showprice/{type}")
    public String showMaxPrice(@PathVariable String type) throws BadRequestException {
        log.info("Initated "+type);
        if(type.equals("Highest"))
        return "highest = "+factoryService1.showHighestPrice();

        else if(type.equals("Lowest"))
            return "lowest = "+factoryService1.showLowestPrice();
        else
            throw new BadRequestException("Only available options are Highest and Lowest (case sensitive)");
    }

}

