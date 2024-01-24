package com.example.demo.services;

import com.example.demo.dto.VehicleDTO;
import com.example.demo.dto.VehicleMapper;
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
        Vehicle vh = VehicleMapper.mapToVehicle(v);
        inventoryStore.addVehicle(vh);
    }
    public Vehicle getVehicle(String id)
    {
       return inventoryStore.getVehicleByID(id);
    }
    public boolean updateVehicle(VehicleDTO v, String id)
    {
        Vehicle vh = VehicleMapper.mapToVehicle(v);
       return inventoryStore.updateVehicle(vh,id);
    }
    public boolean deleteVehicle(String id)  {
       return inventoryStore.remove(id);
    }
    public String showHighestPrice()
    {
      List<String> vh= inventoryStore.showHighestPrice();
      return Collections.max(vh);
    }

}
