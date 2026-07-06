package com.kh.mvc.model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.util.DBUtil;

/*
 	[Model - DAO] member 테이블 대한 실제 SQL실행을 담당하는 객체
 	
 	Controller에서는 해당 클래스의 메서드만 호출할 뿐
 	그 안에 어떤 SQL이 실행되는지는 몰라도 된다. (관심사 분리)
 * */
public class MemberDAO {
	
	/*
	 	모든 회원 조회
	 */
	public List<MemberDTO> findAll() throws SQLException {
		List<MemberDTO> list = new ArrayList<>();
		
		String sql = "SELECT id,name, email, age FROM member ORDER BY id DESC";
		
		try(Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO(
									rs.getInt("id"),
									rs.getString("name"),
									rs.getString("email"),
									rs.getInt("age")
								);
				list.add(dto);
			}
			
		}
		
		return list;
	}
	
	public void insert(MemberDTO dto) throws SQLException {
		String sql = "INSERT INTO member(name, email, age) values (?,?,?)";
		
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getEmail());
			pstmt.setInt(3, dto.getAge());
			pstmt.executeUpdate();
		}
	}
	
	public void delete(int id) {
		String sql = "DELETE FROM member WHERE id = ?";
		
		try (Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
