package com.assessment.assessmenttwo.dao;

import com.assessment.assessmenttwo.dto.UserDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import java.util.int;

@Repository
public interface UserDAO extends CrudRepository<UserDTO, Integer> {

    @Query("SELECT s FROM UserDTO s WHERE s.username = :username")
    UserDTO findUserByUsername(String username);
}


