package com.kh.mybatis.model;

import com.kh.mybatis.util.DBUtil;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberDAO {

    public List<MemberDTO> findAll(){
        List<MemberDTO> list = new ArrayList<>();
        String sql = "SELECT id, name, email, age FROM member ORDER BY id DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rset = pstmt.executeQuery()){

            while(rset.next()){
                MemberDTO dto = new MemberDTO(rset.getInt("id"),
                                              rset.getString("name"),
                                              rset.getString("email"),
                                              rset.getInt("age"));
                list.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public int insert(MemberDTO dto){
        String sql = "INSERT INTO member(name, email, age) VALUES(?,?,?)";
        int result = 0;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);){

            pstmt.setString(1, dto.getName());
            pstmt.setString(2, dto.getEmail());
            pstmt.setInt(3, dto.getAge());

            //insert성공으로 생성된 row의 갯수
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
