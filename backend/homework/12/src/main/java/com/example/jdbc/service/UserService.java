package com.example.jdbc.service;

import com.example.jdbc.dao.UserDAO;
import com.example.jdbc.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The UserService class provides business logic operations related to users.
 */
@Service
@Slf4j
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Adds a user to the system.
     *
     * @param  user  the UserDTO object to be added
     * @return      true if the user is successfully added, false otherwise
     */
    public boolean addUser(UserDTO user) {
        try {
            log.info(userDAO.save(user).toString());
            return true;
        } catch (Exception e) {
            log.error("Error occurred while adding user: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retrieves a list of UserDTO objects based on the provided tenant ID.
     *
     * @param  tenantID  the ID of the tenant to retrieve users for
     * @return           a list of UserDTO objects representing the users associated with the tenant
     */
    public List<UserDTO> getUsersByTenant(UUID tenantID) {
        try {
            return userDAO.findByTenantId(tenantID);
        } catch (Exception e) {
            log.error("Error getting users by tenant: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Updates the user with the given username and user ID.
     *
     * @param  username  the new username for the user
     * @param  userid    the unique identifier of the user
     */
    public void updateUser(String username, UUID userid) {
        try {
            Optional<UserDTO> userOptional = userDAO.findById(userid);
            if (userOptional.isPresent()) {
                UserDTO user = userOptional.get();
                user.setUsername(username);
                userDAO.save(user);
                log.info("User updated successfully: {}", user);
            } else {
                log.warn("User with ID {} not found", userid);
            }
        } catch (Exception e) {
            log.error("Exception updating user: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Retrieves all users with pagination.
     *
     * @param  page  the pagination information
     * @return      the page of user data transfer objects
     */
    public Page<UserDTO> findAll(Pageable page) {
        return userDAO.findAll(page);
    }
}
