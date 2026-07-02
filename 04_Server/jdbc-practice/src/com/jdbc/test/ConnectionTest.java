package com.jdbc.test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/back_tdb?serverTimezone=Asia/Seoul&characterEncoding=UTF-8";
        String user = "server";
        String password = "server";
        
        // 수동으로 드라이버 로드 (과거 필수)
				//Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("DB 연결 성공: " + conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
