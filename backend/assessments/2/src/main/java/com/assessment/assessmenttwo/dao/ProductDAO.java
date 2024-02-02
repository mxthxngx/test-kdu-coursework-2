package com.assessment.assessmenttwo.dao;

import com.assessment.assessmenttwo.dto.ProductDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import java.util.int;
@Repository
public interface ProductDAO extends CrudRepository<ProductDTO, Integer> {
}
