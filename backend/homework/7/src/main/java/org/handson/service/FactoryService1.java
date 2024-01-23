package org.handson.service;

import org.handson.components.InventoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


@Service
public class FactoryService1 extends VehicleService{
    private static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(VehicleConfig.class);

    InventoryStore inventoryStore ;

    @Autowired
    public FactoryService1(@Qualifier("factory1") InventoryStore inventoryStore) {
        this.inventoryStore = inventoryStore;
    }


    public void setInventoryStore(InventoryStore inventoryStore)
    {
        this.inventoryStore = inventoryStore;
    }

    @Override
    public void addVehicle() {
        inventoryStore.addVehicle("factory1vehicle");
    }

}
