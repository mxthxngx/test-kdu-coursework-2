package com.kdu.smarthome.service;

import com.kdu.smarthome.dao.UserDAO;
import com.kdu.smarthome.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for managing user-related operations in a smart home system.
 */
@Service
@Slf4j
public class UserService {
    UserDAO userDAO;
    PasswordEncoder passwordEncoder;

    /**
     * Constructor for the UserService class.
     *
     * @param userDAO          The data access object for users.
     * @param passwordEncoder  The encoder for encoding user passwords.
     */
    @Autowired
    UserService(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Saves a user to the system.
     *
     * @param user The UserDTO object representing the user to be saved.
     */
    public void save(UserDTO user) {
        try {
            UserDTO user2 = userDAO.save(user);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Adds a new user to the system.
     *
     * @param person The UserDTO object representing the user to be added.
     */
    public void addUser(UserDTO person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        save(person);
        log.info(person.toString());
    }

    /**
     * Retrieves a user based on their username.
     *
     * @param username The username of the user to be retrieved.
     * @return The UserDTO object representing the specified user.
     * @throws Exception If the specified username is not found.
     */
    public UserDTO getUserByUsername(String username) throws Exception {
        try {
            UserDTO user = userDAO.findByUsername(username.trim());
            if (user == null) {
                log.info(userDAO.findAll().toString());
                throw new UsernameNotFoundException("Username not found");
            }
            log.info("User found by username: " + user.toString());
            return user;
        } catch (UsernameNotFoundException e) {
            log.error("Username not found" + e.toString());
            throw e;
        }
    }
}
