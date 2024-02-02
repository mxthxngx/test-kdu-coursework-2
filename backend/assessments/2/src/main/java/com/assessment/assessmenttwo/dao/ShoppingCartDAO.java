package com.assessment.assessmenttwo.dao;

import com.assessment.assessmenttwo.dto.ShoppingCartDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ShoppingCartDAO extends CrudRepository<ShoppingCartDTO, UUID> {
}
