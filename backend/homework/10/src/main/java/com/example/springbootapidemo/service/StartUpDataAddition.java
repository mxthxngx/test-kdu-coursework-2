package com.example.springbootapidemo.service;

import com.example.springbootapidemo.dao.UserDAO;
import com.example.springbootapidemo.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StartUpDataAddition implements CommandLineRunner {

    UserDAO userDAO;
    PasswordEncoder passwordEncoder;

    @Autowired
    StartUpDataAddition(UserDAO userDAO,PasswordEncoder passwordEncoder)
    {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }
    /**
     * Run the function with the given arguments.
     *
     * @param  args  the arguments to be passed to the function
     * @return       void
     */
    @Override
    public void run(String... args) throws Exception {
        userDAO.addUser(new UserEntity( "math", passwordEncoder.encode("Testing123"), "ROLE_ADMIN"));
        userDAO.addUser(new UserEntity( "user", passwordEncoder.encode("Testing123"), "ROLE_USER"));
    }
}
