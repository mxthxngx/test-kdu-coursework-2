package com.kdu.smarthome.mapper;

import com.kdu.smarthome.dto.HouseDTO;
import com.kdu.smarthome.dto.UserDTO;
import com.kdu.smarthome.model.HouseModel;
import com.kdu.smarthome.model.HouseRole;
import com.kdu.smarthome.model.HouseUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Mapper class for converting DTOs to models related to houses in a smart home system.
 */
@Slf4j
public class HouseMapper {

    private HouseMapper(){}
    /**
     * Converts a HouseDTO to a HouseModel.
     *
     * @param houseDTO   The HouseDTO object to be converted.
     * @param houseRole  The role associated with the house.
     * @return The corresponding HouseModel object.
     */
    public static HouseModel toHouseModel(HouseDTO houseDTO, HouseRole houseRole) {
        HouseModel houseModel = new HouseModel();
        houseModel.setHouseName(houseDTO.getHouse_name());
        log.info("HOUSEDTO: " + houseDTO.toString());
        houseModel.setAddress(houseDTO.getAddress());
        return houseModel;
    }

    /**
     * Converts a HouseDTO to a HouseUser.
     *
     * @param houseDTO   The HouseDTO object to be converted.
     * @param houseRole  The role associated with the house.
     * @return The corresponding HouseUser object.
     */
    public static HouseUser toHouseUser(HouseDTO houseDTO, HouseRole houseRole) {
        HouseUser houseUser = new HouseUser();
        houseUser.setHouseRole(houseRole);
        String username = currentUserName();
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        houseUser.setUser(userDTO);
        return houseUser;
    }

    /**
     * Retrieves the username of the currently authenticated user.
     *
     * @return The username of the currently authenticated user.
     */
    public static String currentUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
