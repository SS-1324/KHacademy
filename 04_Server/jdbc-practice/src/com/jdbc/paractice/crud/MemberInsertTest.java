package com.jdbc.paractice.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.practice.DBInfo;

public class MemberInsertTest {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름 : ");
		String name = sc.next();
		
		System.out.print("이메일 : ");
		String email = sc.next();
		
		System.out.print("나이 : ");
		int age = sc.nextInt();
		
		String sql = "insert into member(name, email, age) values (?,?,?)";
		
		try (Connection conn = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			//conn.setAutoCommit(false);
			
			//1번째 ?에 name값 넣기
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setInt(3, age);
			
			int result = pstmt.executeUpdate();
			System.out.println(result + "행이 추가됨");
			
			/*
				if(result > 0) {
					conn.commit();
				} else {
					conn.rollback();
				}
			*/
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
