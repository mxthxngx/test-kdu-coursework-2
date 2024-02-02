package com.assessment.assessmenttwo.dao;

import com.assessment.assessmenttwo.dto.InventoryDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface InventoryDAO extends CrudRepository<InventoryDTO, UUID> {
}
