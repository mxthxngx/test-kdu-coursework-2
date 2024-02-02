package com.assessment.assessmenttwo.service;

import com.assessment.assessmenttwo.dao.UserDAO;
import com.assessment.assessmenttwo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserDAO userDAO;
    @Autowired
    UserService(UserDAO userDAO)
    {
        this.userDAO = userDAO;
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
}
