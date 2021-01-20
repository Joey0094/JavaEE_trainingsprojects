package com.example.test;

import com.example.dao.impl.BookDaoImpl;
import com.example.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BookDaoImplTest {

    BookDaoImpl bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        Book book = new Book("红楼梦", "曹雪芹", new BigDecimal(225), 15000, 5000, "");
        bookDao.addBook(book);
    }

    @Test
    public void deleteBook() {
        bookDao.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        Book book = new Book("红楼梦", "高鹗", new BigDecimal(225), 15000, 5000, "");
        book.setId(26);
        bookDao.updateBook(book);
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(26);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        books.forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(0, 50));
    }

    @Test
    public void queryForPageItemsByPrice() {
        System.out.println(bookDao.queryForPageItemsByPrice(1, 4, 0, 50));
    }
}