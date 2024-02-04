package com.kdu.smarthome.mapper;

import com.kdu.smarthome.dto.AddDeviceDTO;
import com.kdu.smarthome.dto.DeviceDTO;
import com.kdu.smarthome.dto.DeviceRegisterDTO;
import com.kdu.smarthome.model.AddDeviceModel;
import com.kdu.smarthome.model.DeviceModel;
import com.kdu.smarthome.model.RegisteredDevice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Mapper class for converting DTOs to models and vice versa related to devices in a smart home system.
 */
public class DeviceMapper {
    
    private DeviceMapper(){}

    /**
     * Converts a DeviceDTO to a DeviceModel.
     *
     * @param deviceDTO The DeviceDTO object to be converted.
     * @return The corresponding DeviceModel object.
     */
    public static DeviceModel toDeviceModel(DeviceDTO deviceDTO) {
        DeviceModel deviceModel = new DeviceModel();
        deviceModel.setKickstonId(deviceDTO.getKickston_id());
        deviceModel.setDeviceUsername(deviceDTO.getDevice_username());
        deviceModel.setDevicePassword(deviceDTO.getDevice_password());

        LocalDateTime manufactureDateTime = LocalDateTime.parse(
                deviceDTO.getManufacture_date_time(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS")
        );
        deviceModel.setManufactureDateTime(manufactureDateTime);

        deviceModel.setManufactureFactoryPlace(deviceDTO.getManufacture_factory_place());

        return deviceModel;
    }

    /**
     * Converts a DeviceRegisterDTO to a RegisteredDevice.
     *
     * @param deviceRegisterDTO The DeviceRegisterDTO object to be converted.
     * @return The corresponding RegisteredDevice object.
     */
    public static RegisteredDevice toRegisteredDevice(DeviceRegisterDTO deviceRegisterDTO) {
        RegisteredDevice registeredDevice = new RegisteredDevice();

        // Add mapping logic here if needed

        return registeredDevice;
    }

    /**
     * Converts an AddDeviceDTO to an AddDeviceModel.
     *
     * @return The corresponding AddDeviceModel object.
     */
    public static AddDeviceModel toAddDeviceModel() {
        return new AddDeviceModel();
    }
}
