package com.kdu.smarthome.dao;

import com.kdu.smarthome.dto.RoomDTO;
import com.kdu.smarthome.model.HouseUser;
import com.kdu.smarthome.model.RoomModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the data access object (DAO) interface for managing RoomModel entities.
 * It extends the CrudRepository interface provided by Spring Data JPA.
 */
@Repository
public interface RoomDAO extends CrudRepository<RoomModel, Integer> {

    /**
     * Retrieve a room by its ID and the ID of its associated house.
     * @param roomID the ID of the room to retrieve.
     * @param houseID the ID of the house associated with the room.
     * @return the RoomModel entity corresponding to the roomID and houseID.
     */
    @Query("SELECT room FROM RoomModel room WHERE room.id = :id AND room.houseModel.id = :houseID")
    RoomModel findIdAndHouseId(@Param("id") Integer roomID, @Param("houseID") Integer houseID);
}
