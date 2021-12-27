package com.passgym.zzim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.passgym.exception.AddException;
import com.passgym.exception.RemoveException;
import com.passgym.sql.PassGymConnection;
import com.passgym.zzim.vo.Zzim;

public class ZzimDAOOracle implements ZzimDAOInterface {
	private static ZzimDAOOracle dao = new ZzimDAOOracle();
	
	private ZzimDAOOracle() {}
	
	public static ZzimDAOOracle getInstance() {
		return dao;
	}
	
	public void addZzim(Zzim zzim) throws AddException{
		Connection con = null;
		PreparedStatement pstmt = null;
		String removeZzimSQL = "INSERT INTO zzim VALUES(?,?);";
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(removeZzimSQL);
			pstmt.setInt(1, zzim.getUser().getUserNo());
			pstmt.setInt(2, zzim.getGym().getOwnerNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			PassGymConnection.close(pstmt, con);
		}
	}
	
	public void removeZzim(Zzim zzim) throws RemoveException{
		Connection con = null;
		PreparedStatement pstmt = null;
		String removeZzimSQL = "DELETE FROM zzim WHERE user_no = ? AND owner_no = ?";
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(removeZzimSQL);
			pstmt.setInt(1, zzim.getUser().getUserNo());
			pstmt.setInt(2, zzim.getGym().getOwnerNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			PassGymConnection.close(pstmt, con);
		}
	}
	
	public static void main(String[] args) {
		
	}
}
