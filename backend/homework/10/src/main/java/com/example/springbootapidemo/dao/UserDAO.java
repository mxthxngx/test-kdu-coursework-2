package com.example.springbootapidemo.dao;

import com.example.springbootapidemo.model.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDAO {
    List<UserEntity> personList;

    public UserDAO() {
        this.personList = new ArrayList<>();
    }

    public void addUser(UserEntity person){
        personList.add(person);
    }

    public UserEntity getUserByIdx(int index){
        return personList.get(index);
    }

    public String getRoleByUserIdx(int index){
        return personList.get(index).getRole();
    }

    public List<UserEntity> getAllUsers(){
        return personList;
    }
}
