package com.example.web;

import com.example.pojo.*;
import com.example.service.BookService;
import com.example.service.OrderService;
import com.example.service.impl.BookServiceImpl;
import com.example.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet{

    OrderService orderService = new OrderServiceImpl();

    /*
    1. 生成单个订单
    2. 将购物车清空
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart.getCartItems() == null) {
            resp.sendRedirect(req.getHeader("Referer"));
            return;
        }
        User user = (User) req.getSession().getAttribute("user");
        if(user == null) {
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
            return;
        }
        String orderId = orderService.createOrder(cart, user.getId());
        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /*
    1. 展示所有订单的概况
     */
    protected void showOrders(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }

    /*
    1. 在新的页面查看订单的详情
     */
    protected void showOrderDetails(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }

    /*
    1. 查看当前用户的订单
     */
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        if(user == null) {
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
            return;
        }
        List<Order> myOrders = orderService.queryMyOrder(user.getId());
        req.setAttribute("myOrders", myOrders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);

    }

    /*
    1. 寄出订单
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }

    /*
    1. 签收订单
     */
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }
}
