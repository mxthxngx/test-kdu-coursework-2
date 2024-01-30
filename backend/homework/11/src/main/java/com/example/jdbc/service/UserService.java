package com.example.jdbc.service;

import com.example.jdbc.dao.UserDAO;
import com.example.jdbc.dto.UserDTO;
import com.example.jdbc.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean addUser(UserDTO user) {
        try {
            return userDAO.addUser(user) != 0;
        } catch (Exception e) {
            log.error("Error: "+e);
            throw e;

        }
    }
    public List<UserDTO> getUsersByTenant(UUID tenantID) {
        try {
            return userDAO.getUsersByTenant(tenantID);
        } catch (Exception e) {
            log.error("Error getting users by tenant: " + e);
            throw e;
        }
    }
    public void updateUser(String username,UUID userid)
    {
        try
        {
            userDAO.updateUser(username,userid);
            log.info("Updated user");
        }
        catch (Exception e)
        {
            log.error("Exception at updateUser: "+e);
            throw e;
        }
    }
}
