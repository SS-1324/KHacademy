package com.jdbc.paractice.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.practice.DBInfo;

public class MemberUpdateTest {

	public static void main(String[] args) {
		// 변경할 member의 id를 입력받고 변경할 email을 입력받아서
		// 입력받은 id를 가진 member의 email을 입력받은 email으로 변경
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("변경할 Member의 ID를 입력하세요 : ");
		int id = sc.nextInt();
		
		System.out.print("새로운 Email을 입력하세요 : ");
		String email = sc.next();
		
		String sql = "UPDATE member SET email = ? WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, email);
			pstmt.setInt(2, id);
			
			int reuslt = pstmt.executeUpdate();
			System.out.println(reuslt + "행의 이메일이 "+ email +"로 변경됨");
			
			
		} catch(SQLException e){
			e.printStackTrace();
		}
	}

}
