package com.assessment.assessmenttwo.dao;

import com.assessment.assessmenttwo.dto.InventoryDTO;
import com.assessment.assessmenttwo.dto.ShoppingCartDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//import java.util.int;
@Repository
public interface InventoryDAO extends CrudRepository<InventoryDTO, Integer> {
    @Query("SELECT s FROM InventoryDTO s WHERE s.productDTO.id = :productID")
    InventoryDTO findByProductID(int productID);
}
