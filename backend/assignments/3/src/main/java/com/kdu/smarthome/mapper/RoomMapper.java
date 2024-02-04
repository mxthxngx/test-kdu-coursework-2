package com.kdu.smarthome.mapper;

import com.kdu.smarthome.dto.RoomDTO;
import com.kdu.smarthome.model.HouseModel;
import com.kdu.smarthome.model.RoomModel;

/**
 * Mapper class for converting DTOs to models related to rooms in a smart home system.
 */
public class RoomMapper {

    private RoomMapper(){}
    /**
     * Converts a RoomDTO to a RoomModel.
     *
     * @param roomDTO     The RoomDTO object to be converted.
     * @param houseModel  The HouseModel object to which the room belongs.
     * @return The corresponding RoomModel object.
     */
    public static RoomModel DTOModelMapper(RoomDTO roomDTO, HouseModel houseModel) {
        RoomModel roomModel = new RoomModel();
        HouseModel houseModel1 = houseModel;
        houseModel1.setHouseName(houseModel1.getHouseName());
        roomModel.setRoomName(roomDTO.getRoom_name());
        roomModel.setHouseModel(houseModel1);
        return roomModel;
    }
}
