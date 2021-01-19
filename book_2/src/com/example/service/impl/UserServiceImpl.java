package com.example.service.impl;

import com.example.dao.impl.UserDaoImpl;
import com.example.pojo.User;
import com.example.service.UserService;

public class UserServiceImpl extends UserDaoImpl implements UserService {

    @Override
    public void registUser(User user) {
        saveUser(user);
    }

    @Override
    public User login(User user) {
        return queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        return queryUserByUsername(username) != null;
    }
}
