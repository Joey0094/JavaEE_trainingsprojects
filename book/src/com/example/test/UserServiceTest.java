package com.example.test;

import com.example.pojo.User;
import com.example.service.UserService;
import com.example.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null, "bbj123", "11111", "bbj123@123.com"));
        userService.registUser(new User(null, "bbj1234", "111112", "bbj1234@123.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "wzg168", "123456", null)));
    }

    @Test
    public void existsUsername() {
        if(userService.existsUsername("bbj123")) {
            System.out.println("用户名已存在");
        } else {
            System.out.println("用户名可用");
        }
    }
}
