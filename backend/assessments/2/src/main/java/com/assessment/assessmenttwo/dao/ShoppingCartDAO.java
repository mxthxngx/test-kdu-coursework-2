package com.assessment.assessmenttwo.dao;

import com.assessment.assessmenttwo.dto.ShoppingCartDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
//import java.util.int;
@Repository
public interface ShoppingCartDAO extends CrudRepository<ShoppingCartDTO, Integer> {

    @Query("SELECT s FROM ShoppingCartDTO s WHERE s.user.id = :userID")
    List<ShoppingCartDTO> findUserByUserId(int userID);

    @Query("SELECT s FROM ShoppingCartDTO s WHERE s.productDTO.id = :productID AND s.user.id = :userID")
    ShoppingCartDTO findProductById(int productID,int userID);

}

