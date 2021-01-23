package com.example.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    public static ThreadLocal<Connection> conn = new ThreadLocal<>();

    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
//            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
//            String path = System.getProperty("user.dir") + "\\src\\main\\java\\jdbc.properties";
//            FileInputStream inputStream = new FileInputStream(path);
            properties.load(inputStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println(JdbcUtils.class.getClassLoader());
    }

    public static Connection getConnection() {

        if(conn.get() == null) {
            try {
                conn.set(dataSource.getConnection());
                // 设置手动提交事件
                conn.get().setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return conn.get();
    }

    public static void commitAndCloseConnection() {

        if(conn.get() != null) {
            try {
                conn.get().commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.get().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // release Thread
        conn.remove();
    }

    public static void rollbackAndCloseConnection() {

        if(conn.get() != null) {
            try {
                conn.get().rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.get().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conn.remove();
    }

}
