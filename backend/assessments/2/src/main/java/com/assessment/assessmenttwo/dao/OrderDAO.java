package com.assessment.assessmenttwo.dao;

import com.assessment.assessmenttwo.dto.OrderDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import java.util.int;
@Repository
public interface OrderDAO extends CrudRepository<OrderDTO, Integer> {
}
