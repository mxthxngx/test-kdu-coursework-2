package com.assessment.assessmenttwo.service;

import com.assessment.assessmenttwo.dao.UserDAO;
import com.assessment.assessmenttwo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserDAO userDAO;
    PasswordEncoder passwordEncoder;


    @Autowired
    UserService(UserDAO userDAO,PasswordEncoder passwordEncoder)
    {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }
    public void save(UserDTO user)
    {
        try {
            UserDTO user2 = userDAO.save(user);
        }
        catch (Exception e)
        {
            throw  e;
        }
    }
    /**
     * Adds a user to the system.
     *
     * @param  person   the user entity to be added
     * @return          void
     */
    public void addUser(UserDTO person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        save(person);
    }
    public UserDTO getUserByUsername(String username)
    {
        try {
            return userDAO.findUserByUsername( username);
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
