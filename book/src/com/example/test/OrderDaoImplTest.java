package com.example.test;

import com.example.dao.OrderDao;
import com.example.dao.impl.OrderDaoImpl;
import com.example.pojo.Order;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoImplTest {

    @Test
    public void saveOrder() {
    }

    @Test
    public void queryMyOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        List<Order> orders = orderDao.queryMyOrder(1);
        System.out.println(orders);
    }
}