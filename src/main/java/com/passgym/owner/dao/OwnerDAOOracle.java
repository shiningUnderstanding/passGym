package com.passgym.owner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.gym.vo.Gym;
import com.passgym.owner.vo.Owner;
import com.passgym.sql.PassGymConnection;

public class OwnerDAOOracle implements OwnerDAOInterface {

	@Override
	public Owner findByOwnerId(String id) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT * FROM owner WHERE id=?";
		
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int ownerNo = rs.getInt("owner_no");
				String pwd = rs.getString("pwd");
				int ownerStatus = rs.getInt("owner_status");
				Owner owner = new Owner(ownerNo, id, pwd, ownerStatus);
				return owner;
			}else {
				throw new FindException("아이디에 해당하는 고객이 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			PassGymConnection.close(rs, pstmt, con);
		}
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
