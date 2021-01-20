package com.example.service.impl;

import com.example.dao.BookDao;
import com.example.dao.OrderDao;
import com.example.dao.OrderItemDao;
import com.example.dao.impl.BookDaoImpl;
import com.example.dao.impl.OrderDaoImpl;
import com.example.dao.impl.OrderItemDaoImpl;
import com.example.pojo.*;
import com.example.service.OrderService;

import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        // 保证 orderId 的唯一性
        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        orderDao.saveOrder(order);

        for(CartItem item : cart.getCartItems().values()) {
            OrderItem orderItem = new OrderItem(null, item.getName(), item.getCount(), item.getPrice(), item.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);

            Book book = bookDao.queryBookById(item.getId());
            book.setSales(book.getSales() + item.getCount());
            book.setStock(book.getStock() - item.getCount());
            bookDao.updateBook(book);
        }

        cart.clear();

        return orderId;
    }

    @Override
    public List<Order> queryMyOrder(Integer id) {
        return orderDao.queryMyOrder(id);
    }
}
