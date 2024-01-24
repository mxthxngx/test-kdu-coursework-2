package com.example.demo.dto;

import com.example.demo.model.Vehicle;

public class VehicleMapper {
    public static VehicleDTO mapToVehicleDTO(Vehicle vehicle) {
        VehicleDTO vehicleDTO = new VehicleDTO(vehicle.getId(), vehicle.getPrice());
        return vehicleDTO;
    }
    public static Vehicle mapToVehicle(VehicleDTO vehicleDTO)
    {
        Vehicle vehicle = new Vehicle(vehicleDTO.getId(),vehicleDTO.getPrice());
        return vehicle;
    }
}
