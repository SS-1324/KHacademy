package com.jdbc.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.jdbc.practice.DBInfo;

/*
 * 트랜잭션 - 계좌이체
 * 
 * A계좌(id=1)에서 B계좌(id=2)로 1000원을 이체.
 * */
public class AccountTransferTest {

	public static void main(String[] args) {
		int amount = 1000;
		
		try (Connection conn = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASSWORD)){
			conn.setAutoCommit(false); // 수동커밋을 하겠다.
			String sql1 = "UPDATE account SET balance = balance - ? where id = 1";
			String sql2 = "UPDATE account SET balance = balance + ? where id = 2";
			
			PreparedStatement withdraw = conn.prepareStatement(sql1);
			PreparedStatement deposit = conn.prepareStatement(sql2);
			
			withdraw.setInt(1, amount);
			int result1 = withdraw.executeUpdate();
			
			deposit.setInt(1, amount);
			int result2 = deposit.executeUpdate();
			
			if(result1 * result2 > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
