/**
 * The TenantDAO interface provides access to tenant data in the database.
 * It extends the CrudRepository interface.
 */
package com.example.jdbc.dao;

import com.example.jdbc.dto.TenantDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TenantDAO extends CrudRepository<TenantDTO, UUID> {
}
