package com.passgym.owner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.passgym.exception.AddException;
import com.passgym.gym.vo.Gym;
import com.passgym.owner.vo.Owner;
import com.passgym.sql.PassGymConnection;

public class OwnerDAOOracle implements OwnerDAOInterface {

	@Override
	public Owner findByOwnerId(String ownerNo) {
		
		return null;
	}

	@Override
	public void add(Owner owner) throws AddException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String insertSQL = "INSERT INTO owner(owner_no, id, pwd) VALUES(?,?,?)";
		
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setInt(1, owner.getOwnerNo());
			pstmt.setString(2, owner.getId());
			pstmt.setString(3, owner.getPwd());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if(errorCode == 1) {
				throw new AddException("이미 존재하는 아이디입니다.");
			}else {
				e.printStackTrace();
				throw new AddException(e.getMessage());
			}
		}finally {
			PassGymConnection.close(pstmt, con);
		}
	}

}
