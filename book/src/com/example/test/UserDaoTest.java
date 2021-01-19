package com.example.test;

import com.example.dao.UserDao;
import com.example.dao.impl.UserDaoImpl;
import org.junit.Test;

public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        if(userDao.queryUserByUsername("admin1234") == null) {
            System.out.println("用户名可用！");
        } else {
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if(userDao.queryUserByUsernameAndPassword("admin", "admin123") == null) {
            System.out.println("用户名或密码错误，登录失败！");
        } else {
            System.out.println("查询成功！");
        }
    }
}
