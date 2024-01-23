package org.handson.service;

import jakarta.annotation.PostConstruct;
import org.handson.*;
import org.handson.components.InventoryStore;
import org.handson.components.Vehicle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Configuration
abstract public class VehicleService {

   private static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(VehicleConfig.class);

    public void addVehicle()
    {
        InventoryStore inventoryStore = (InventoryStore) context.getBean("vehicle");
        inventoryStore.addVehicle("factory2");
    }

    /**
     * Calculate and log the vehicle with the highest price in the vehicle list.
     */
    public void highestPrice()
    {
        
        double maximumPrice = 0;
        Vehicle v = null;
        List<Vehicle> vehicleList = InventoryStore.getVehicleList();
        for(var vehicle : vehicleList)
        {
            if(maximumPrice<vehicle.getPrice())
            {  maximumPrice=vehicle.getPrice();
            v = vehicle;}
            MyLogger.customLogger(vehicle.toString(),"DEBUG");
        }
        if(v!=null)
            MyLogger.customLogger("The maximum price belongs to : "+v.toString(),"DEBUG");
    
        else
            MyLogger.customLogger("Error: object null","ERROR");
        }


    public void lowestPrice()
    {

        double lowestPrice = 0;
        Vehicle v = null;
        List<Vehicle> vehicleList = InventoryStore.getVehicleList();
        for(var vehicle : vehicleList)
        {
            if(lowestPrice<vehicle.getPrice())
            {  lowestPrice=vehicle.getPrice();
                v = vehicle;}
            MyLogger.customLogger(vehicle.toString(),"DEBUG");
        }
        if(v!=null)
            MyLogger.customLogger("The lowest price belongs to : "+v.toString(),"DEBUG");

        else
            MyLogger.customLogger("Error: object null","ERROR");
    }


}
