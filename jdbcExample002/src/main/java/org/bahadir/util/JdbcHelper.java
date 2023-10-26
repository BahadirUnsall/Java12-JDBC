package org.bahadir.util;

import org.bahadir.util.constant.JdbcConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcHelper {

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JdbcConstant.url,JdbcConstant.username,JdbcConstant.password);
            System.out.println("Bağlantı başarılı");
        } catch (SQLException e) {
            System.out.println("Connection hatası: " + e.getMessage());
        }
        return connection;
    }
}
