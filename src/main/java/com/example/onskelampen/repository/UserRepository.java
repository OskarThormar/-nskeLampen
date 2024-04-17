package com.example.onskelampen.repository;

import com.example.onskelampen.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public class UserRepository {

    private ArrayList<User> userList;
    private int userId;

    public UserRepository() {
        userId = 1;
        this.userList = new ArrayList<>();
        userList.add(new User("Oskar", "1234"));
        userList.add(new User("Jesper", "1234"));
        userList.add(new User("Mikkel", "1234"));
    }
    private int getUserId() {
        return userId++;
    }


    public User findByUserName(String userName) {
        for(User user : userList){
            if (user != null) {
                if(user.getUserName().equals(userName)){
                    return user;
                }
            }
        }
        return null;
    }

    public ArrayList<User> getAllUsers() {
        return userList;
    }

    public boolean authenticate(String userName, String password) {
        for (User user : userList) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return true; // Authentication successful
            }
        }
        return false;
    }
    public boolean registerUser(User newUser) {
        // Check if the username already exists
        for (User existingUser : userList) {
            if (existingUser.getUserName().equals(newUser.getUserName())) {
                return false; // Username already exists, cannot register this user
            }
        }
        userList.add(newUser); // Add new user if username is unique
        return true;
    }

    public boolean validateUser(String userName, String password) {
        User user = findByUserName(userName);
        if (user != null) {
            return user.getPassword().equals(password);  // Simple password check
        }
        return false;
    }
}
