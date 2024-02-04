package com.kdu.smarthome.service;

import com.kdu.smarthome.dao.HouseUserDAO;
import com.kdu.smarthome.dao.RoomDAO;
import com.kdu.smarthome.dto.RoomDTO;
import com.kdu.smarthome.exception.custom.GenericException;
import com.kdu.smarthome.mapper.HouseMapper;
import com.kdu.smarthome.mapper.RoomMapper;
import com.kdu.smarthome.model.HouseModel;
import com.kdu.smarthome.model.HouseUser;
import com.kdu.smarthome.model.RoomModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing operations related to rooms in a smart home system.
 */
@Service
public class RoomService {
    HouseService houseService;
    RoomDAO roomDAO;
    HouseUserDAO houseDAO;

    /**
     * Constructor for the RoomService class.
     *
     * @param houseService The service for managing house-related operations.
     * @param roomDAO      The data access object for rooms.
     * @param houseUser    The data access object for house users.
     */
    @Autowired
    RoomService(HouseService houseService, RoomDAO roomDAO, HouseUserDAO houseUser) {
        this.houseService = houseService;
        this.roomDAO = roomDAO;
        this.houseDAO = houseUser;
    }

    /**
     * Adds a new room to the system.
     *
     * @param room     The data transfer object containing room details.
     * @param houseID  The ID of the house to which the room belongs.
     * @return The created RoomModel object.
     * @throws Exception If an error occurs during the room creation process.
     */
    public RoomModel addRoom(RoomDTO room, Integer houseID) throws Exception {
        HouseModel houseModel = houseService.getHouseById(houseID);
        Optional<HouseUser> houseUserOpt = houseDAO.findById(houseID);

        if (houseUserOpt.isPresent()) {
            HouseUser houseUser = houseUserOpt.get();
            if (HouseMapper.currentUserName().equals(houseUser.getUser().getUsername())) {
                RoomModel roomModel = RoomMapper.dtomodelmapper(room, houseModel);
                roomDAO.save(roomModel);
                return roomModel;
            } else {
                throw new GenericException("Current user is not an admin");
            }
        } else {
            throw new GenericException("House with ID " + houseID + " not found");
        }
    }

    /**
     * Retrieves a room based on its ID.
     *
     * @param roomId The ID of the room to be retrieved.
     * @return The RoomModel object representing the specified room.
     */
    public RoomModel getRoomById(int roomId) {
        Optional<RoomModel> optionalRoomModel = roomDAO.findById(roomId);
        return optionalRoomModel.orElse(null);
    }

    /**
     * Retrieves a room based on its ID and the ID of the house it belongs to.
     *
     * @param roomId  The ID of the room to be retrieved.
     * @param houseId The ID of the house to which the room belongs.
     * @return The RoomModel object representing the specified room.
     */
    public RoomModel getRoomByIdAndHouseId(int roomId, int houseId) {
        return roomDAO.findIdAndHouseId(roomId, houseId);
    }
}
