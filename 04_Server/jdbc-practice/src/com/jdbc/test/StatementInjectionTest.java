package com.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.practice.DBInfo;

/*
 * Statement객체 활용
 * 
 * JDBC용 객체
 * - Connection : DB의 연결정보를 담고있는 객체(연결객체이기때문에 사용 후 반납)
 * - Statement : 연결된 DB에 sql문을 전달해서 실행한 뒤, 결과를 받아내는 객체
 * - ResultSet : 쿼리문(select)실행 후 결과를 담는 객체
 * */
public class StatementInjectionTest {
	public static void main(String[] args) {
		Connection conn = null;
		
		// searchName은 사용자로부터 입력받은 값 가정
		//String searchName = "최지원";
		String searchName = "' or '1' = '1";
		
		String sql = "select * from member where name = '"+searchName+"'";
		
		try {
			//데이터베이스 연결객체 생성
			conn = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD);
			
			//기본적으로 auto commit상태이므로 혹시 트랜잭션을 활용하고싶다면
			// conn.setAutoCommit(false); // auto commit 사용 하지 않겠다.
			// conn.commit(); 
			// conn.rollback();
			// 를 통해서 트랜잭션을 제어할 수 있다.
			
			//sql을 전달해서 값을 받아오기위한 객체
			Statement stmt = conn.createStatement();
			
			//Statement객체를 통해서 sql을 전달할 때
			// insert, update, delete -> .executeUpdate(sql); -> 처리된 행의 수 (int)
			// select -> .executeQuery(sql); -> 조회결과 (ResultSet)
			ResultSet rset = stmt.executeQuery(sql);
			
			// ResultSet객체의 값을 가져오는 방식은 커서방식으로
			// next()가 실행될때마다 커서를 한칸 아래로 내리면서 가져올 데이터가 있는지를 boolean으로 반환해준다.
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
