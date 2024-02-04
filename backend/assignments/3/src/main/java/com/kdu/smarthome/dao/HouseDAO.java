package com.kdu.smarthome.dao;

import com.kdu.smarthome.model.HouseModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository interface for interacting with the database entity HouseModel.
 * Provides CRUD (Create, Read, Update, Delete) operations for HouseModel entities.
 */
@Repository
public interface HouseDAO extends CrudRepository<HouseModel, Integer> {

}
