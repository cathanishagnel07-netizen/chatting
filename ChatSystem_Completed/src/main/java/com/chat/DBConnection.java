package com.chat;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://db01.dbhost.dev:5051/db_454pzrx5k?useSSL=true&serverTimezone=UTC";
    private static final String USER = "user_454pzrx5k";
    private static final String PASSWORD = "p454pzrx5k";

    private DBConnection() {}

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
