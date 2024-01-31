/**
 * The ShiftDAO interface provides access to shift data in the database.
 * It extends the CrudRepository interface.
 */
package com.example.jdbc.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import com.example.jdbc.dto.ShiftDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftDAO extends CrudRepository<ShiftDTO, UUID> {

    /**
     * Retrieves a list of shifts by tenant ID.
     * @param tenantId the UUID of the tenant.
     * @return a list of ShiftDTO objects associated with the specified tenant.
     */
    List<ShiftDTO> findByTenantId(UUID tenantId);

    /**
     * Retrieves the top 3 shifts within the specified date range, ordered by shift name.
     * @param startDate the start date of the range.
     * @param endDate the end date of the range.
     * @return a list of ShiftDTO objects within the specified date range, ordered by shift name.
     */
    @Query("SELECT s FROM ShiftDTO s WHERE s.dateStart = :startDate AND s.dateEnd = :endDate ORDER BY s.name ASC")
    List<ShiftDTO> findTop3ByDateRange(Timestamp startDate, Timestamp endDate);

}
