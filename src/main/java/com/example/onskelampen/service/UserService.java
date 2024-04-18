package com.example.onskelampen.service;

import com.example.onskelampen.model.User;
import com.example.onskelampen.repository.UserRepository;
import com.example.onskelampen.repository.UserRepository_DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository_DB userRepository_db;


    public boolean validate(String userName, String password) {
//        return userRepository.validateUser(userName, password);
        return userRepository_db.validateUser(userName, password);
    }
    public boolean register(User newUser) {
//        return userRepository.registerUser(newUser);
        return userRepository_db.register(newUser);
    }

    public boolean loginUser(String username, String password) {
        return userRepository_db.loginUser(username, password);
    }

    public int findUserId(String userName) {
        return userRepository_db.findUserId(userName);
    }
}
