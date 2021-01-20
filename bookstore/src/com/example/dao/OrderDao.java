package com.example.dao;

import com.example.pojo.Order;

import java.util.List;

public interface OrderDao {

    public int saveOrder(Order order);

    public List<Order> queryMyOrder(Integer id);

}
