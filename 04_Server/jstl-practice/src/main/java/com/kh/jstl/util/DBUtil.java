package com.kh.jstl.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 	DB접속 정보를 한곳에서 관리하기위한 객체.
 	단순하게 접속 정보 뿐만 아니라 Connection을 직접 만들어서 반환.
 	DAO에서 바로 호출해서 쓸 수 있도록 함.
 * */
public class DBUtil {
	public static final String URL = "jdbc:mysql://localhost:3306/back_tdb?serverTimezone=Asia/Seoul&characterEncoding=UTF-8";
	public static final String USER = "server";
	public static final String PASSWORD = "server";
	
	public static Connection getConnection() throws SQLException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
