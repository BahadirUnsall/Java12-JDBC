package com.bahadir.util;

import com.bahadir.util.constant.JdbcConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private Connection connection;
    private static DbConnection instance;

    private DbConnection(){
        createConnection();
    }

    public Connection getConnection(){
        return connection;
    }

    public static DbConnection getInstance(){
        if (instance == null){
            instance = new DbConnection();
        }
        return instance;
    }

    private void createConnection(){
        try {
            connection = DriverManager.getConnection(JdbcConstant.url,JdbcConstant.username,JdbcConstant.password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
