package com.kdu.smarthome.dao;

import com.kdu.smarthome.model.HouseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseUserDAO extends JpaRepository<HouseUser,Integer> {

    /**
     * Find all house users by username.
     * @param username the username of the user.
     * @return a list of HouseUser entities.
     */
    @Query("SELECT hu FROM HouseUser hu WHERE hu.user.username = ?1")
    List<HouseUser> findAllByUserUsername(String username);

    /**
     * Find a house user by username and house ID.
     * @param username the username of the user.
     * @param houseID the ID of the house.
     * @return a HouseUser entity.
     */
    @Query("SELECT hu FROM HouseUser hu WHERE hu.user.username = :username AND hu.house.id = :houseID")
    HouseUser findAllByUserUsernameAndHouseId(@Param("username") String username, @Param("houseID") Integer houseID);

    /**
     * Find all house users by house ID.
     * @param houseID the ID of the house.
     * @return a list of HouseUser entities.
     */
    @Query("SELECT hu FROM HouseUser hu WHERE hu.house.id = :houseID")
    List<HouseUser> findAllByHouseId( @Param("houseID") Integer houseID);

}
