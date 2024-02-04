package com.kdu.smarthome.dao;

import com.kdu.smarthome.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the data access object (DAO) interface for managing UserDTO entities.
 * It extends the JpaRepository interface provided by Spring Data JPA.
 */
@Repository
public interface UserDAO extends JpaRepository<UserDTO, Integer> {

    /**
     * Find a user by username.
     * @param username the username of the user to retrieve.
     * @return the UserDTO entity corresponding to the username.
     */
    UserDTO findByUsername(String username);
}
