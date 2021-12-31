package com.passgym.userqna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.passgym.exception.AddException;
import com.passgym.sql.PassGymConnection;
import com.passgym.userqna.vo.UserQna;

public class UserQnaDAOOracle implements UserQnaDAOInterface {
	private static UserQnaDAOOracle dao = new UserQnaDAOOracle();
	
	private UserQnaDAOOracle() {}
	
	public static UserQnaDAOOracle getInstance() {
		return dao;
	} 

	@Override
	public void addUserQna(UserQna userQna) throws AddException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String addUserQnaSQL = "INSERT INTO user_qna(qna_no, title, content, reply, user_no)\r\n"
				+ "VALUES(user_qna_seq.nextval,?,?, null, ?)";
		
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(addUserQnaSQL);
			pstmt.setString(1, userQna.getTitle());
			pstmt.setString(2, userQna.getContent());
			pstmt.setInt(3, userQna.getUserNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			PassGymConnection.close(pstmt, con);
		}
	}
}
