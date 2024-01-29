package com.example.springbootapidemo.service;

import com.example.springbootapidemo.dao.UserDAO;
import com.example.springbootapidemo.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserService(UserDAO userDAO)
    {
        this.userDAO=userDAO;
    }
    UserDAO userDAO;

    /**
     * Adds a user to the system.
     *
     * @param  person   the user entity to be added
     * @return          void
     */
    public void addUser(UserEntity person){
        userDAO.addUser(person);
    }

    /**
     * Returns the UserEntity with the given username.
     *
     * @param  name  the username to search for
     * @return       the UserEntity with the given username, or null if not found
     */
    public UserEntity getUserByUsername(String name){
        for(UserEntity p : userDAO.getAllUsers()){
            if(p.getUsername().equals(name)){
                return p;
            }
        }
        return null;
    }

    /**
     * Display all the users.
     *
     * @return         	the list of user entities
     */
    public List<UserEntity> displayAllUsers()
    {
        return userDAO.getAllUsers();
    }
}
