package org.handson;

import org.handson.service.FactoryService1;
import org.handson.service.FactoryService2;
import org.handson.service.VehicleConfig;
import org.handson.service.VehicleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /**
     * Main method to initialize VehicleService, add vehicles, and find the highest price.
     *
     * @param  args	array of command-line arguments
     * @return     	void
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(VehicleConfig.class);


        FactoryService1 vehicleService = context.getBean(FactoryService1.class);

        for(int i = 0; i <10; i++)
        {
            vehicleService.addVehicle();
        }

        vehicleService.highestPrice();
        vehicleService.lowestPrice();


        FactoryService2 vehicleService2 = context.getBean(FactoryService2.class);


        for(int i = 0; i <10; i++)
        {
            vehicleService.addVehicle();
        }

        vehicleService.highestPrice();
        for(int i = 0; i <10; i++)
        {
            vehicleService2.addVehicle();
        }

        vehicleService2.highestPrice();
        vehicleService2.lowestPrice();
    }
}