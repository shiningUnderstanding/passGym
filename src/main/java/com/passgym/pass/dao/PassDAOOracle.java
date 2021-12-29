package com.passgym.pass.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.passgym.exception.AddException;
import com.passgym.pass.vo.Pass;
import com.passgym.sql.PassGymConnection;

public class PassDAOOracle implements PassDAOInterface {


	@Override
	public void add(Pass pass) throws AddException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(List<Pass> passes) throws AddException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String insertSQL = "INSERT INTO pass(owner_no, pass_no, pass_name, pass_price, pass_status, pass_month, pause_count, pause_date, remarks)\r\n"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		for(int i = 0; i < passes.size(); i++) {
			Pass pass = passes.get(i);
			try {
				con = PassGymConnection.getConnection();
				pstmt = con.prepareStatement(insertSQL);
				pstmt.setInt(1, pass.getOwnerNo());
				pstmt.setInt(2, pass.getPassNo());
				pstmt.setString(3, pass.getPassName());
				pstmt.setInt(4, pass.getPassPrice());
				pstmt.setInt(5, pass.getPassStatus());
				pstmt.setInt(6, pass.getPassMonth());
				pstmt.setInt(7, pass.getPauseCount());
				pstmt.setInt(8, pass.getPauseDate());
				pstmt.setString(9, pass.getRemarks());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				PassGymConnection.close(pstmt, con);
			}
		}
		
		
		
	}


}
