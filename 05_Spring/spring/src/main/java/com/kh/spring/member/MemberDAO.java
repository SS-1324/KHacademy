package com.kh.spring.member;

import com.kh.spring.util.DBUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
    DAO : 실제 데이터베이스 접근하는 객체

    @Repository : @Component의 기능을 포함하고, DB접근계층이라는 의미를 더한다.
* */

@Repository
public class MemberDAO {

    public List<MemberDTO> findAll(){
        List<MemberDTO> list = new ArrayList<>();
        String sql = "SELECT id, name, email, age FROM member";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rset = pstmt.executeQuery();){

            // rset.next() : select로 가져온 결과를 커서방식으로 하나씩 가르키면서 다음 데이터 존재여부를 반환
            while (rset.next()) {
                MemberDTO dto = new MemberDTO(
                        rset.getInt("id"),
                        rset.getString("name"),
                        rset.getString("email"),
                        rset.getInt("age")
                );
                list.add(dto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void insert(MemberDTO dto) {
        String sql = "INSERT INTO member(name, email, age) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);){

            pstmt.setString(1, dto.getName());
            pstmt.setString(2, dto.getEmail());
            pstmt.setInt(3, dto.getAge());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id){
        String sql = "DELETE FROM member WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);){

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
