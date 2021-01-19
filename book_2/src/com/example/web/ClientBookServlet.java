package com.example.web;

import com.example.pojo.Book;
import com.example.pojo.Page;
import com.example.service.BookService;
import com.example.service.impl.BookServiceImpl;
import com.example.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNo, pageSize);

        page.setUrl("client/bookServlet?action=page");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String minStr = req.getParameter("min");
        String maxStr = req.getParameter("max");
        // 获取请求的参数
        int min = WebUtils.parseInt(minStr, 0);
        int max = WebUtils.parseInt(maxStr, Integer.MAX_VALUE);

        if((min > max) || (min == 0 && max == Integer.MAX_VALUE)) {
            resp.sendRedirect(req.getContextPath());
            return;
        }

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);

        if(min <= 0) {
            minStr = "";
        } else if(max >= Integer.MAX_VALUE) {
            maxStr = "";
        }

        page.setUrl("client/bookServlet?action=pageByPrice" + "&min=" + minStr + "&max=" + maxStr);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp?min=" + minStr + "&max=" + maxStr).forward(req, resp);
    }
}
