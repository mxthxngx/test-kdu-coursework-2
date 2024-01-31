/**
 * The UserDAO interface provides access to user data in the database.
 * It extends both CrudRepository and PagingAndSortingRepository interfaces.
 */
package com.example.jdbc.dao;

import com.example.jdbc.dto.UserDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserDAO extends CrudRepository<UserDTO, UUID>, PagingAndSortingRepository<UserDTO, UUID> {

    /**
     * Retrieves a list of users associated with the specified tenant ID.
     *
     * @param tenantId The UUID of the tenant.
     * @return A list of UserDTO objects associated with the specified tenant ID.
     */
    List<UserDTO> findByTenantId(UUID tenantId);
}
