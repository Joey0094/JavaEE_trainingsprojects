package com.example.service;

import com.example.pojo.Cart;
import com.example.pojo.Order;

import java.util.List;

public interface OrderService {

    public String createOrder(Cart cart, Integer userId);

    public List<Order> queryMyOrder(Integer id);
}
