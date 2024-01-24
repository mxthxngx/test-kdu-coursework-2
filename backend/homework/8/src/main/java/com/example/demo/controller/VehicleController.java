package com.example.demo.controller;
import com.example.demo.dto.VehicleDTO;
import com.example.demo.exception.CustomException;
import com.example.demo.model.Vehicle;
import com.example.demo.services.FactoryService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController

//@RequestMapping("/vehicles")
public class VehicleController {

    private FactoryService1 factoryService1;
    @Autowired  VehicleController(FactoryService1 factoryService1)
    {
        this.factoryService1=factoryService1;
    }

    private List<Vehicle> vehiclesList = new ArrayList<>();

    @PostMapping("factory1/addvehicle")
    public ResponseEntity<String> addVehicle(@RequestBody @Valid VehicleDTO vehicle)
    {
        factoryService1.addVehicle(vehicle);
        return ResponseEntity.ok("Successfully Added!");
    }

    @GetMapping("/factory1/{id}")
    public Vehicle showVehicle(@PathVariable String id)
    {
       return factoryService1.getVehicle(id);
    }

    @PutMapping("/factory1/update/{id}")
    public ResponseEntity<String> updateFactory(@PathVariable String id, @RequestBody VehicleDTO vehicle)
    {
    if( factoryService1.updateVehicle(vehicle,id))
     return ResponseEntity.ok("Updated vehicle");
    else
        return ResponseEntity.ok("Something went wrong! Most likely could not find id");
    }
    @DeleteMapping("/factory1/delete/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable String id)
    {

            if(factoryService1.deleteVehicle(id))
                return ResponseEntity.ok("Deleted vehicle");

            else   return ResponseEntity.ok("Something went wrong! Most likely could not find id");
    }

    @GetMapping("/factory1/showprice")
    public String showMaxPrice()
    {
        return factoryService1.showHighestPrice();
    }

}

