package com.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.practice.DBInfo;

/*
 * PreparedStatement
 * Statement와 동일한 역할을 하는 객체로 사용방식을 조금 변경한 형태다.
 * 
 * PreparedStatement의 동일하게 searchName("' or '1' = '1") 넣어도
 * 이름이 정확하게 문자열인 회원을 찾을 뿐 전체조회로 이어지지 않는다.
 * */

public class PreparedStatementTest {

	public static void main(String[] args) {
		Connection conn = null;
		
		//String searchName = "최지원";
		String searchName = "' or '1' = '1";
		
		//sql구성시 변수로 값을 대체할 자리에 ?를 넣어줌
		String sql = "select * from member where name = ?";
		
		try {
			//데이터베이스 연결객체 생성
			conn = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD);
			
			//sql을 전달해서 값을 받아오기위한 객체생성시 -> 미완성 sql문 전달
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//sql의 ?부분을 완성
			pstmt.setString(1, searchName);
			
			
			//Statement객체를 통해서 sql을 전달할 때
			ResultSet rset = pstmt.executeQuery();
			
			// ResultSet객체의 값을 가져오는 방식은 커서방식으로
			int count = 0;
			while(rset.next()) {
				count++;
				System.out.println(rset.getInt("id") + ", " + rset.getString("name") + ", " + rset.getString("email"));
			}
			System.out.println("조회된 회원 수 : " + count);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
