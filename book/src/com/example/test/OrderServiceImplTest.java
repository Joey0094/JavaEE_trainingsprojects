package com.example.test;

import com.example.dao.OrderDao;
import com.example.dao.impl.OrderDaoImpl;
import com.example.pojo.Book;
import com.example.pojo.Cart;
import com.example.pojo.CartItem;
import com.example.pojo.Order;
import com.example.service.OrderService;
import com.example.service.impl.OrderServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class OrderServiceImplTest {

    @Test
    public void createOrder() {
        OrderService orderService = new OrderServiceImpl();

        Cart cart = new Cart();
        Book book = new Book("Book3", "autor1", new BigDecimal(2), 2, 2, null);
        Book book1 = new Book("Book3", "autor1", new BigDecimal(2), 2, 2, null);
        Book book2 = new Book("Book4", "autor2", new BigDecimal(20), 20, 20, null);
        book.setId(3);
        book1.setId(3);
        book2.setId(4);
        cart.addItem(book);
        cart.addItem(book1);
        cart.addItem(book2);
        Integer userId = 2;

        String orderId = orderService.createOrder(cart, userId);
        System.out.println(orderId);
    }

    @Test
    public void queryMyOrder() {
        OrderService orderService = new OrderServiceImpl();
        List<Order> orders = orderService.queryMyOrder(1);
        System.out.println(orders);
    }
}