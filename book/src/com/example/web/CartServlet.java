package com.example.web;

import com.example.pojo.Book;
import com.example.pojo.Cart;
import com.example.service.BookService;
import com.example.service.impl.BookServiceImpl;
import com.example.utils.WebUtils;
import javafx.scene.chart.Chart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        int id = WebUtils.parseInt(req.getParameter("id"), -1);
        Book book = bookService.queryBookById(id);
        cart.addItem(book);
        req.getSession().setAttribute("lastName", book.getName());
        // 原页面地址
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        int id = WebUtils.parseInt(req.getParameter("id"), -1);
        cart.deleteItem(id);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/cart.jsp");
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clear();
        resp.sendRedirect(req.getContextPath() + "/pages/cart/cart.jsp");
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        int id = WebUtils.parseInt(req.getParameter("id"), -1);
        int count = WebUtils.parseInt(req.getParameter("count"), -1);
        if(count <= 0) {
            resp.sendRedirect(req.getContextPath() + "/pages/cart/cart.jsp");
            return;
        }
        cart.getCartItems().get(id).setCount(count);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/cart.jsp");
    }


}
