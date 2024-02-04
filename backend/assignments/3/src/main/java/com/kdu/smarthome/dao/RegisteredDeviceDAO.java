package com.kdu.smarthome.dao;

import com.kdu.smarthome.model.RegisteredDevice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * This is the data access object (DAO) interface for managing RegisteredDevice entities.
 * It extends the CrudRepository interface provided by Spring Data JPA.
 */
public interface RegisteredDeviceDAO extends CrudRepository<RegisteredDevice,Integer> {

    /**
     * Retrieve a registered device by its kickstonId.
     * @param kickstonId the kickstonId of the device to retrieve.
     * @return the RegisteredDevice entity corresponding to the kickstonId.
     */
    @Query("SELECT d FROM RegisteredDevice d WHERE d.deviceModel.kickstonId = :kickstonId")
    RegisteredDevice findByKickstonId(String kickstonId);
}
