package com.kh.mybatis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL =
            "jdbc:mysql://localhost:3306/back_tdb?serverTimezone=Asia/Seoul&characterEncoding=UTF-8";
    private static final String USERNAME = "server";
    private static final String PASSWORD = "server";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
