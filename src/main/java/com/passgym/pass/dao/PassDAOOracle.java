package com.passgym.pass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.pass.vo.Pass;
import com.passgym.sql.PassGymConnection;

public class PassDAOOracle implements PassDAOInterface {

	@Override
	public void add(Pass pass) throws AddException {
		
	}

	@Override
	public Pass findByPassNo(int passNo) throws FindException {
		Pass pass = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT * FROM pass WHERE pass_no = ?";
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, passNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int ownerNo = rs.getInt("owner_no");
				passNo = rs.getInt("pass_no");
				String passName = rs.getString("pass_name");
				int passPrice = rs.getInt("pass_price");
				Date passDate = rs.getDate("pass_date");
				int passStatus = rs.getInt("pass_status");
				int passMonth = rs.getInt("pass_month");
				int pauseCount = rs.getInt("pause_count");
				int puaseDate = rs.getInt("pause_date");
				String remarks = rs.getString("remarks");
			}else {
				throw new FindException("해당 번호의 회원권이 없습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pass;
	}
	
	@Override
	public List<Pass> findPassByOwnerNo(int ownerNo) throws FindException{
		List<Pass> passes = new ArrayList<>();
		return passes;
	}


}
