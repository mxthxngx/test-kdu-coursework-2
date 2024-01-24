package com.example.demo.services;

import com.example.demo.dto.VehicleDTO;
import com.example.demo.dto.VehicleMapper;
import com.example.demo.logger.MyLogger;
import com.example.demo.model.Vehicle;
import com.example.demo.repository.InventoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FactoryService1 implements FactoryService{

    InventoryStore inventoryStore;
    @Autowired
    FactoryService1(InventoryStore inventoryStore)
    {
        this.inventoryStore = inventoryStore;
    }
    public void addVehicle(VehicleDTO v)
    {
        MyLogger.customLogger("adding vehicle","INFO");
        Vehicle vh = VehicleMapper.mapToVehicle(v);
        inventoryStore.addVehicle(vh);
    }
    public Vehicle getVehicle(String id)
    { MyLogger.customLogger("getting vehicle","INFO");
       return inventoryStore.getVehicleByID(id);
    }
    public boolean updateVehicle(VehicleDTO v, String id)
    {
        MyLogger.customLogger("updating vehicle","INFO");
        Vehicle vh = VehicleMapper.mapToVehicle(v);
       return inventoryStore.updateVehicle(vh,id);
    }
    public boolean deleteVehicle(String id)  {
       return inventoryStore.remove(id);
    }
    public String showHighestPrice()
    { MyLogger.customLogger("getting the highest price","INFO");
      List<String> vh= inventoryStore.showHighestPrice();
      return Collections.max(vh);
    }
    public String showLowestPrice()
    {
        MyLogger.customLogger("getting the lowest price","INFO");
        List<String> vh= inventoryStore.showLowestPrice();
        return Collections.min(vh);
    }

}
