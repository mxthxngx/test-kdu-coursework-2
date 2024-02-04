/**
 * Service class for managing operations related to houses in a smart home system.
 * This class includes methods for adding houses, adding users to houses, retrieving house details,
 * updating house information, and fetching a list of houses associated with the current user.
 */
package com.kdu.smarthome.service;

import com.kdu.smarthome.dao.AddDeviceDAO;
import com.kdu.smarthome.dao.HouseDAO;
import com.kdu.smarthome.dao.HouseUserDAO;
import com.kdu.smarthome.dto.HouseDTO;
import com.kdu.smarthome.dto.UserDTO;
import com.kdu.smarthome.mapper.HouseMapper;
import com.kdu.smarthome.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing operations related to houses in a smart home system.
 * This class includes methods for adding houses, adding users to houses, retrieving house details,
 * updating house information, and fetching a list of houses associated with the current user.
 */
@Service
@Slf4j
public class HouseService {
    HouseDAO houseDAO;
    HouseUserDAO houseUserDAO;
    AddDeviceDAO addDeviceDAO;
    UserService userService;

    /**
     * Constructor for the HouseService class.
     *
     * @param houseDAO      The data access object for houses.
     * @param houseUserDAO  The data access object for house users.
     * @param userService   The service for managing user-related operations.
     * @param addDeviceDAO  The data access object for adding devices.
     */
    @Autowired
    HouseService(HouseDAO houseDAO, HouseUserDAO houseUserDAO, UserService userService, AddDeviceDAO addDeviceDAO) {
        this.houseDAO = houseDAO;
        this.houseUserDAO = houseUserDAO;
        this.userService = userService;
        this.addDeviceDAO = addDeviceDAO;
    }

    /**
     * Adds a new house to the system.
     *
     * @param houseDTO The data transfer object containing house details.
     * @param admin    The role of the user adding the house (typically ADMIN).
     * @return The created HouseModel object.
     * @throws Exception If an error occurs during the house creation process.
     */
    public HouseModel addHouse(HouseDTO houseDTO, HouseRole admin) throws Exception {
        try {
            HouseModel houseModel = HouseMapper.toHouseModel(houseDTO, admin);
            houseModel = houseDAO.save(houseModel);
            HouseUser houseUser = HouseMapper.toHouseUser(houseDTO, admin);
            UserDTO userDTO = userService.getUserByUsername(houseUser.getUser().getUsername());
            if (userDTO == null) {
                throw new RuntimeException("UserDTO not found for username: " + houseUser.getUser().getUsername());
            }
            houseUser.setUser(userDTO);
            houseUser.setHouse(houseModel);
            houseUserDAO.save(houseUser);
            log.info("HouseUser: " + houseUser);
            log.info("HouseModel: " + houseModel);
            return houseModel;
        } catch (Exception e) {
            log.error("Something went wrong in house creation: " + e);
            throw e;
        }
    }

    /**
     * Adds a user to an existing house.
     *
     * @param houseId  The ID of the house to which the user will be added.
     * @param newUser  The username of the user to be added.
     * @return The name of the added user.
     * @throws Exception If an error occurs during the user addition process.
     */
    public String addUserToHouse(Integer houseId, String newUser) throws Exception {
        try {
            String currentUser = HouseMapper.currentUserName();
            log.info("Current username: " + currentUser);
            log.info("Username to be added: " + newUser);
            Optional<HouseUser> houseModel = houseUserDAO.findById(houseId);
            if (houseModel.isPresent()) {
                HouseUser house = houseModel.get();
                log.info(house.toString());
                // Check for admin
                if (house.getUser().getUsername().equals(currentUser)) {
                    UserDTO userDTO = userService.getUserByUsername(newUser);
                    log.info(userDTO.toString());
                    HouseUser houseUser = new HouseUser();
                    houseUser.setUser(userDTO);
                    houseUser.setHouseRole(HouseRole.BASIC);
                    houseUser.setHouse(house.getHouse());
                    houseUserDAO.save(houseUser);

                    log.info("Successfully added the new user");
                    return userDTO.getName();
                } else {
                    log.error("Current user not admin");
                    throw new RuntimeException("User not admin");
                }
            }

            log.error("Unsuccessful in adding");
            throw new Error("Unable to add user");
        } catch (Exception e) {
            log.error("Something went wrong in house member addition: " + e);
            throw e;
        }
    }

    /**
     * Retrieves the details of a house based on its ID.
     *
     * @param houseId The ID of the house to be retrieved.
     * @return The HouseModel object representing the specified house.
     * @throws Exception If the specified house ID is not found.
     */
    public HouseModel getHouseById(Integer houseId) throws Exception {
        try {
            Optional<HouseModel> optionalHouseModel = houseDAO.findById(houseId);
            if (optionalHouseModel.isPresent()) {
                return optionalHouseModel.get();
            } else {
                throw new Exception("House with ID " + houseId + " not found");
            }
        } catch (Exception e) {
            log.error("Failed to retrieve house by ID: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Updates the address of a house based on its ID.
     *
     * @param houseId    The ID of the house to be updated.
     * @param newAddress The new address for the house.
     * @return The updated HouseModel object.
     * @throws Exception If the specified house ID is not found.
     */
    public HouseModel updateHouseAddress(Integer houseId, String newAddress) throws Exception {
        try {
            Optional<HouseModel> optionalHouseModel = houseDAO.findById(houseId);
            if (optionalHouseModel.isPresent()) {
                HouseModel houseModel = optionalHouseModel.get();
                houseModel.setAddress(newAddress);
                return houseDAO.save(houseModel);
            } else {
                throw new Exception("House with ID " + houseId + " not found");
            }
        } catch (Exception e) {
            log.error("Failed to update house address: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retrieves a list of houses associated with the current user.
     *
     * @return The list of HouseModel objects associated with the current user.
     */
    public List<HouseModel> getAllHouses() {
        try {
            String currentUser = HouseMapper.currentUserName();
            List<HouseUser> houseUsers = houseUserDAO.findAllByUserUsername(currentUser);
            log.info("User:" + currentUser + " houseUserslist: " + houseUsers.size());
            List<HouseModel> userHouses = new ArrayList<>();

            for (HouseUser houseUser : houseUsers) {
                log.info("house users:" + houseUsers);
                Integer houseId = houseUser.getHouse().getId();
                HouseModel houseModel = houseDAO.findById(houseId).orElse(null);
                if (houseModel != null || houseModel.getAddress() == null) {
                    userHouses.add(houseModel);
                }
            }
            log.info(houseUsers.toString());
            log.info(userHouses.toString());

            return userHouses;

        } catch (Exception e) {
            log.error("Unable to get houses: " + e);
            throw e;
        }
    }

    /**
     * Retrieves details of all devices and rooms associated with a specific house.
     *
     * @param houseID The ID of the house for which details are to be retrieved.
     * @return A string containing details of devices and rooms associated with the house.
     */
    public String getAllDetails(Integer houseID) {
        List<Object[]> roomsAndDevices = addDeviceDAO.findHouseDeviceRoomByHouseId(houseID);
        StringBuilder result = new StringBuilder();
        for (Object[] row : roomsAndDevices) {
            HouseModel houseModel = (HouseModel) row[0];
            DeviceModel deviceModel = (DeviceModel) row[1];
            RoomModel roomModel = (RoomModel) row[2];

            result.append("House: ").append(houseModel.toString()).append(", ");
            result.append("Device: ").append(deviceModel.toString()).append(", ");
            result.append("Room: ").append(roomModel.toString()).append("\n");
        }
        return result.toString();
    }

    public HouseUser findByHouseIDAndUsername(Integer houseID, String username)
    {
        return houseUserDAO.findAllByUserUsernameAndHouseId(username,houseID);
    }
}
