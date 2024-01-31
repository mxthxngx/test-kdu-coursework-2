/**
 * The ShiftUserDAO interface provides access to shift user data in the database.
 * It extends the CrudRepository interface.
 */
package com.example.jdbc.dao;

import com.example.jdbc.dto.ShiftUserDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ShiftUserDAO extends CrudRepository<ShiftUserDTO, UUID> {

    /**
     * Retrieves a list of shift users by tenant ID.
     * @param tenantId the UUID of the tenant.
     * @return a list of ShiftUserDTO objects associated with the specified tenant.
     */
    List<ShiftUserDTO> findByTenantId(UUID tenantId);
}
