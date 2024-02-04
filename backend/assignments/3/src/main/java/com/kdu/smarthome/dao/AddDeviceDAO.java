/**
 * This is the data access object (DAO) interface for managing AddDeviceModel entities.
 * It extends the CrudRepository interface provided by Spring Data JPA.
 */
package com.kdu.smarthome.dao;

import com.kdu.smarthome.model.AddDeviceModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddDeviceDAO extends CrudRepository<AddDeviceModel,Integer> {

    /**
     * Custom query to find house, device, and room information by house ID.
     * @param houseId the ID of the house for which to retrieve information.
     * @return a list of Object arrays where each array contains houseModel, deviceModel, and roomModel.
     */
    @Query("SELECT ad.houseModel, ad.deviceModel, ad.roomModel FROM AddDeviceModel ad WHERE ad.houseModel.id = :houseId")
    List<Object[]> findHouseDeviceRoomByHouseId(@Param("houseId") Integer houseId);

    @Query("SELECT ad FROM AddDeviceModel ad WHERE ad.deviceModel.kickstonId = :kickston_id")
    AddDeviceModel findByKickStonId(@Param("kickston_id") String kickstonId);

}
