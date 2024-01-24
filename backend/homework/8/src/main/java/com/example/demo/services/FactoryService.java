package com.example.demo.services;

import com.example.demo.dto.VehicleDTO;
import com.example.demo.model.Vehicle;

interface FactoryService {
    public void addVehicle(VehicleDTO v);

    public Vehicle getVehicle(String id);

    public boolean updateVehicle(VehicleDTO vehicle, String id);

    public boolean deleteVehicle(String id) ;

    public String showHighestPrice();


}
