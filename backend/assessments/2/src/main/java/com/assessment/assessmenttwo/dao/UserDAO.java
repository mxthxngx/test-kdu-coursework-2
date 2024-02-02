package com.assessment.assessmenttwo.dao;

import com.assessment.assessmenttwo.dto.UserDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDAO extends CrudRepository<UserDTO, UUID> {

}
