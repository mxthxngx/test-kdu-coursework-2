package com.kdu.smarthome.dao;

import com.kdu.smarthome.model.DeviceModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * This is the data access object (DAO) interface for managing DeviceModel entities in the inventory.
 * It extends the CrudRepository interface provided by Spring Data JPA.
 */
public interface InventoryDAO extends CrudRepository<DeviceModel,Integer> {

    /**
     * Retrieve a device by its kickstonId.
     * @param kickstonId the kickstonId of the device to retrieve.
     * @return the DeviceModel entity corresponding to the kickstonId.
     */
    @Query("SELECT d FROM DeviceModel d WHERE d.kickstonId = :kickstonId")
    DeviceModel findByKickstonId(String kickstonId);

}
