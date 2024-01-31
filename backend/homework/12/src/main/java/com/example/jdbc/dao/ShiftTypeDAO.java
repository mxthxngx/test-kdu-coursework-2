/**
 * The ShiftTypeDAO interface provides access to shift type data in the database.
 * It extends the CrudRepository interface.
 */
package com.example.jdbc.dao;

import com.example.jdbc.dto.ShiftTypeDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ShiftTypeDAO extends CrudRepository<ShiftTypeDTO, UUID> {

    /**
     * Retrieves a list of shift types by tenant ID.
     * @param tenantId the UUID of the tenant.
     * @return a list of ShiftTypeDTO objects associated with the specified tenant.
     */
    List<ShiftTypeDTO> findByTenantId(UUID tenantId);

}
