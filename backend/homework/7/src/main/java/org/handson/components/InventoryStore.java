package org.handson.components;

import jakarta.annotation.PostConstruct;
import org.handson.service.VehicleConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;
@Configuration
public class InventoryStore {
    private static
    List<Vehicle> vehicleList = new ArrayList<>();
    private static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(VehicleConfig.class);

    /**
     * Adds a vehicle to the vehicle list.
     *
     */

    public void addVehicle(String factory)
    {
        vehicleList.add(context.getBean(factory,Vehicle.class));
    }

    public static List<Vehicle> getVehicleList()
    {
        return vehicleList;
    }


}
