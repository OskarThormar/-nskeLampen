package com.example.onskelampen.service;

import com.example.onskelampen.model.User;
import com.example.onskelampen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public boolean authenticate(String userName, String password) {
        return userRepository.authenticate(userName, password);
    }

    public boolean validate(String userName, String password) {
        return userRepository.validateUser(userName, password);
    }
    public boolean register(User newUser) {
        return userRepository.registerUser(newUser);
    }

    public ArrayList<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
