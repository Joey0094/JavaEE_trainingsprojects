package com.example.test;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr;
import com.example.pojo.Book;
import com.example.pojo.Cart;
import org.junit.Test;

import java.math.BigDecimal;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        Book book = new Book("Book1", "autor1", new BigDecimal(1), 1, 1, null);
        Book book1 = new Book("Book1", "autor1", new BigDecimal(1), 1, 1, null);
        Book book2 = new Book("Book2", "autor2", new BigDecimal(10), 10, 10, null);
        book.setId(1);
        book1.setId(1);
        book2.setId(2);
        cart.addItem(book);
        cart.addItem(book1);
        cart.addItem(book2);
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        Book book = new Book("Book1", "autor1", new BigDecimal(1), 1, 1, null);
        Book book1 = new Book("Book1", "autor1", new BigDecimal(1), 1, 1, null);
        Book book2 = new Book("Book2", "autor2", new BigDecimal(10), 10, 10, null);
        book.setId(1);
        book1.setId(1);
        book2.setId(2);
        cart.addItem(book);
        cart.addItem(book1);
        cart.addItem(book2);
        System.out.println(cart);
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        Book book = new Book("Book1", "autor1", new BigDecimal(1), 1, 1, null);
        Book book1 = new Book("Book1", "autor1", new BigDecimal(1), 1, 1, null);
        Book book2 = new Book("Book2", "autor2", new BigDecimal(10), 10, 10, null);
        book.setId(1);
        book1.setId(1);
        book2.setId(2);
        cart.addItem(book);
        cart.addItem(book1);
        cart.addItem(book2);
        System.out.println(cart);
        cart.deleteItem(1);
        System.out.println(cart);
        cart.clear();
        System.out.println(cart);
    }
}