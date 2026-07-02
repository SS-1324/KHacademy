package com.jdbc.paractice.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.practice.DBInfo;

public class MemberDeleteTest {

	public static void main(String[] args) {
		//member의 ID을 입력받아 입력받은 이름의 데이터를 member테이블에서 삭제해라.

		Scanner sc = new Scanner(System.in);
		
		System.out.print("삭제할 ID를 입력하세요 : ");
		int id = sc.nextInt();
		
		String sql = "delete from member where id = ?";
		try (Connection conn = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setInt(1, id);
			
			int reuslt = pstmt.executeUpdate();
			System.out.println(reuslt + "행이 삭제됨");
			
		} catch(SQLException e){
			e.printStackTrace();
		}
	}

}
