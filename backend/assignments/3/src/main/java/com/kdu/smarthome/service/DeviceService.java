package com.kdu.smarthome.service;

import com.kdu.smarthome.dao.AddDeviceDAO;
import com.kdu.smarthome.dao.RegisteredDeviceDAO;
import com.kdu.smarthome.dto.AddDeviceDTO;
import com.kdu.smarthome.dto.DeviceRegisterDTO;
import com.kdu.smarthome.dto.UserDTO;
import com.kdu.smarthome.exception.custom.CustomItemNotFoundException;
import com.kdu.smarthome.exception.custom.GenericException;
import com.kdu.smarthome.mapper.DeviceMapper;
import com.kdu.smarthome.mapper.HouseMapper;
import com.kdu.smarthome.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import javax.validation.ValidationException;
import java.util.regex.Pattern;

/**
 * Service class for handling device-related operations.
 */
@Service
@Slf4j
public class DeviceService {

    private RegisteredDeviceDAO registeredDeviceDAO;
    private final UserService userService;
    private final InventoryService inventoryService;
    private final HouseService houseService;
    private final RoomService roomService;

    private final AddDeviceDAO addDeviceDAO;

    /**
     * Constructor for DeviceService.
     *
     * @param registeredDeviceDAO The DAO for registered devices.
     * @param userService         The service for user-related operations.
     * @param inventoryService    The service for inventory-related operations.
     * @param houseService        The service for house-related operations.
     * @param roomService         The service for room-related operations.
     * @param addDeviceDAO        The DAO for adding devices.
     */
    @Autowired
    public DeviceService(RegisteredDeviceDAO registeredDeviceDAO, UserService userService,
                         InventoryService inventoryService, HouseService houseService,
                         RoomService roomService, AddDeviceDAO addDeviceDAO) {
        this.registeredDeviceDAO = registeredDeviceDAO;
        this.userService = userService;
        this.inventoryService = inventoryService;
        this.houseService = houseService;
        this.roomService = roomService;
        this.addDeviceDAO = addDeviceDAO;
    }

    /**
     * Registers a device with the specified information.
     *
     * @param deviceRegisterDTO The DTO containing device registration details.
     * @return The registered device.
     * @throws CredentialException If credentials are invalid.
     */
    public RegisteredDevice register(DeviceRegisterDTO deviceRegisterDTO) throws CredentialException, GenericException {
        try {
            String currentUser = HouseMapper.currentUserName();
            UserDTO user = userService.getUserByUsername(currentUser);
            DeviceModel deviceModel = inventoryService.getDeviceByInventory(deviceRegisterDTO.getKickston_id());
            log.info("DEVICE REGISTER:" + deviceRegisterDTO);
            log.info("DEVICE MODEL:" + deviceModel);
            if (user != null && deviceModel != null) {
                if (!deviceModel.getDeviceUsername().equals(deviceRegisterDTO.getDevice_username())) {
                    log.error("Device or user not found");
                    throw new ValidationException("Username or device not found");
                }

                if (deviceModel.getDevicePassword().equals(deviceRegisterDTO.getDevice_password())) {
                    RegisteredDevice registeredDevice = DeviceMapper.toRegisteredDevice(deviceRegisterDTO);
                    registeredDevice.setUserDTO(user);
                    registeredDevice.setDeviceModel(deviceModel);
                    registeredDeviceDAO.save(registeredDevice);
                    return registeredDevice;
                } else {
                    log.error("Incorrect credentials");
                    throw new CredentialException("Invalid credentials");
                }
            } else {
                log.error("Device or user not found");
                throw new ValidationException("Username or device not found");
            }
        } catch (ValidationException | CredentialException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error: " + e.getMessage());
            throw new GenericException("Unexpected error occurred"+e);
        }
    }

    /**
     * Adds a device with the specified information.
     *
     * @param addDeviceRequest The DTO containing device addition details.
     * @return The added device model.
     * @throws Exception If an unexpected error occurs.
     */
    public DeviceModel addDevice(AddDeviceDTO addDeviceRequest) throws Exception {
        AddDeviceModel addDeviceModel = DeviceMapper.toAddDeviceModel();
        String ID = addDeviceRequest.getHouseId();
        Pattern pattern = Pattern.compile("^\\d+$");

        if(ID==null ||!pattern.matcher(ID).matches())
        {
            throw new CustomItemNotFoundException("House ID not found");
        }
        int idInteger = Integer.parseInt(ID);

        String roomId = addDeviceRequest.getRoomId();
        if(roomId==null ||!pattern.matcher(roomId).matches())
        {
            throw new CustomItemNotFoundException("Room ID not found");
        }
        int roomIdInteger = Integer.parseInt(roomId);
        String kickstonId = addDeviceRequest.getKickstonId();

        RegisteredDevice registeredDevice = registeredDeviceDAO.findByKickstonId(kickstonId);
        AddDeviceModel deviceModel1= addDeviceDAO.findByKickStonId(kickstonId);

        HouseModel houseModel = houseService.getHouseById(idInteger);
        log.info("HouseModel: "+houseModel);

        // checking if the house ID is valid
        if (houseModel == null) {
            throw new CustomItemNotFoundException("House ID not found");
        }
        RoomModel roomModel = roomService.getRoomByIdAndHouseId(roomIdInteger, houseModel.getId());

        // checking if the room ID is valid and if the room belongs to that particular house
        if (roomModel == null) {
            throw new CustomItemNotFoundException("Room ID not found");
        }
        log.info("RoomModel: "+roomModel);
        // checking if the device ID is valid and registered
        if (registeredDevice == null) {
            throw new CustomItemNotFoundException("Device ID not found");
        }
        log.info("registered model: "+registeredDevice);


        String currentUser = HouseMapper.currentUserName();
        HouseUser houseUser = houseService.houseUserDAO.findAllByUserUsernameAndHouseId(currentUser, houseModel.getId());
        if (houseUser == null) {
            throw new RuntimeException("User not registered under this house");
        }
        DeviceModel deviceModel = inventoryService.getDeviceByInventory(kickstonId);
        if(deviceModel1!=null && deviceModel1.getHouseModel().getId()==idInteger){
           RoomModel roomModel1 = roomService.getRoomByIdAndHouseId(deviceModel1.getRoomModel().getId(),idInteger);
            deviceModel1.setRoomModel(roomModel1);
            addDeviceDAO.save(deviceModel1);
            return  deviceModel1.getDeviceModel();
        }
      else if(deviceModel1==null) {
            addDeviceModel.setHouseModel(houseModel);
            addDeviceModel.setRoomModel(roomModel);
            addDeviceModel.setDeviceModel(deviceModel);
            if (houseUser.getHouseRole().equals(HouseRole.ADMIN))
                addDeviceModel.setHouseUser(houseUser);

            else {
                throw new Exception("user not admin");
            }

            addDeviceDAO.save(addDeviceModel);
            return deviceModel;
        }
      else {
          throw new Exception("Unable to move or add device");
        }


    }
}
