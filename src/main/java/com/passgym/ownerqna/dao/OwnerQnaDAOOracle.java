package com.passgym.ownerqna.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.passgym.exception.AddException;
import com.passgym.ownerqna.vo.OwnerQna;
import com.passgym.sql.PassGymConnection;
import com.passgym.userqna.dao.UserQnaDAOOracle;

public class OwnerQnaDAOOracle implements OwnerQnaDAOInterface {
	private static OwnerQnaDAOOracle dao = new OwnerQnaDAOOracle();
	private OwnerQnaDAOOracle() {}
	public static OwnerQnaDAOOracle getInstance() {
		return dao;
	}
	
	@Override
	public void addOwnerQna(OwnerQna ownerQna) throws AddException{
		Connection con = null;
		PreparedStatement pstmt = null;
		String addOwnerQnaSQL = "INSERT INTO owner_qna(qna_no, title, content, reply, owner_no)\r\n"
				+ "VALUES(owner_qna_seq.nextval,?,?, null, ?)";
		
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(addOwnerQnaSQL);
			pstmt.setString(1, ownerQna.getTitle());
			pstmt.setString(2, ownerQna.getContent());
			pstmt.setInt(3,  ownerQna.getOwnerNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			PassGymConnection.close(pstmt, con);
		}
	}
}
