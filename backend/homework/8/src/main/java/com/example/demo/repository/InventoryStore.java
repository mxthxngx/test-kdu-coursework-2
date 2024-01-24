package com.example.demo.repository;

import com.example.demo.model.Vehicle;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Data
@Repository
public class InventoryStore {
    @Autowired
   private List<Vehicle> vehicleList = new ArrayList<>();
   public void addVehicle(Vehicle v)
   {
       vehicleList.add(v);
   }
   public Vehicle getVehicleByID(String id)
   {
    for(var vehicle : vehicleList)
    {
       if (vehicle.getId().equals(id))
       {
           return vehicle;
       }


    }
       return null;
   }

   public boolean updateVehicle(Vehicle v,String id)
   {
       for(var vehicle: vehicleList)
       {
           if(vehicle.getId().equals(id))
           {
               System.out.println("Updating vehicle of id to "+id+" from "+v.getId());
               vehicle.setId(v.getId());
               vehicle.setPrice(v.getPrice());
               return true;
              // break;
           }
       }
       return false;
   }
   public boolean remove(String id)
   {
       for(var vehicle: vehicleList)
       {
           if(vehicle.getId().equals(id))
           {
               vehicleList.remove(vehicle);
               return true;

           }
       }
       return false;
   }

   public List<String> showHighestPrice()
   {
       List<String> vehList = new ArrayList<>();
       for(var vehicle: vehicleList)
       {
           vehList.add(String.valueOf(vehicle.getPrice()));
       }
       return vehList;
   }
    public List<String> showLowestPrice()
    {
        List<String> vehList = new ArrayList<>();
        for(var vehicle: vehicleList)
        {
            vehList.add(String.valueOf(vehicle.getPrice()));
        }
        return vehList;
    }


}
