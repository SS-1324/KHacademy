package com.jdbc.paractice.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.practice.DBInfo;

public class MemberSelectTest {

	public static void main(String[] args) {
		String sql = "select * from member";
		
		try (Connection conn = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql)){
		
			
			ResultSet rs = pstmt.executeQuery();
			
			System.out.println("ID | 이름 | 이메일 | 나이");
			System.out.println("----------------------");
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				int age = rs.getInt("age");
				System.out.printf("%d | %s | %s | %d\n", id, name, email, age);
			}
			
			
		} catch(SQLException e){
			e.printStackTrace();
		}

	}

}
