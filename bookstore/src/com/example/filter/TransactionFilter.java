package com.example.filter;

import com.example.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JdbcUtils.commitAndCloseConnection();
        } catch (Exception e) {
            e.printStackTrace();
            JdbcUtils.rollbackAndCloseConnection();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
