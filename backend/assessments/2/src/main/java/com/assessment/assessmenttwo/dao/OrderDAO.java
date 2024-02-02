package com.assessment.assessmenttwo.dao;

import com.assessment.assessmenttwo.dto.OrderDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface OrderDAO extends CrudRepository<OrderDTO, UUID> {
}
