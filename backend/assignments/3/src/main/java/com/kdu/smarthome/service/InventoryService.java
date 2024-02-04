package com.kdu.smarthome.service;

import com.kdu.smarthome.dao.InventoryDAO;
import com.kdu.smarthome.dto.DeviceDTO;
import com.kdu.smarthome.mapper.DeviceMapper;
import com.kdu.smarthome.model.DeviceModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service class for managing operations related to the inventory of devices in a smart home system.
 */
@Service
@Slf4j
public class InventoryService {
    InventoryDAO inventoryDAO;

    /**
     * Constructor for the InventoryService class.
     *
     * @param inventoryDAO The data access object for inventory.
     */
    InventoryService(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    /**
     * Adds a new device to the inventory.
     *
     * @param deviceDTO The data transfer object containing device details.
     * @return The created DeviceModel object.
     */
    public DeviceModel add(DeviceDTO deviceDTO) {
        DeviceModel deviceModel = DeviceMapper.toDeviceModel(deviceDTO);
        try {
            log.info("Device Model: " + deviceModel);
            return inventoryDAO.save(deviceModel);
        } catch (Exception e) {
            log.error("Error in saving to inventory: " + e);
            throw e;
        }
    }

    /**
     * Retrieves details of all devices in the inventory.
     *
     * @return A string representation of all devices in the inventory.
     */
    public String getAllDevices() {
        try {
            return inventoryDAO.findAll().toString();
        } catch (Exception e) {
            log.error("Error: " + e);
            throw e;
        }
    }

    /**
     * Retrieves a device from the inventory based on its kickston_id.
     *
     * @param kickstonId The unique identifier for the device (kickston_id).
     * @return The DeviceModel object representing the specified device.
     * @throws NullPointerException If the specified kickston_id is not found in the inventory.
     */
    public DeviceModel getDeviceByInventory(String kickstonId) {
        DeviceModel deviceModel = inventoryDAO.findByKickstonId(kickstonId);
        if (deviceModel == null) {
            throw new NullPointerException("Kickston_id not found");
        }
        return deviceModel;
    }
}
