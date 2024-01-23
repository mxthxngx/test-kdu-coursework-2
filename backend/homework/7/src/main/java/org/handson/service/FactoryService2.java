package org.handson.service;

import jakarta.annotation.PostConstruct;
import org.handson.components.InventoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class FactoryService2 extends  VehicleService{
    private static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(VehicleConfig.class);

     InventoryStore inventoryStore ;

    @Autowired
    public FactoryService2(@Qualifier("factory1") InventoryStore inventoryStore) {
        this.inventoryStore = inventoryStore;
    }

    @Override
    public void addVehicle() {
        inventoryStore.addVehicle("factory2vehicle");
    }



}

