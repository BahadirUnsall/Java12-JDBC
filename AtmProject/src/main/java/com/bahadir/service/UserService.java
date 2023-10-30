package com.bahadir.service;

import com.bahadir.entity.User;
import com.bahadir.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;
    public UserService(){
        this.userRepository = new UserRepository();
    }
    public User login (String email, String password){
        return userRepository.login(email,password);
    }
}
