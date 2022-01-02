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
	private static PassDAOOracle dao = new PassDAOOracle();
	private PassDAOOracle() {}
	public static PassDAOOracle getInstance() {
		return dao;
	}

	@Override
	public void add(Pass pass) throws AddException {
		
	}

	@Override
	public Pass findPass(int ownerNo, int passNo) throws FindException {
		Pass pass = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT * FROM pass WHERE owner_no =? AND pass_no = ?";
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, ownerNo);
			pstmt.setInt(2, passNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ownerNo = rs.getInt("owner_no");
				passNo = rs.getInt("pass_no");
				String passName = rs.getString("pass_name");
				int passPrice = rs.getInt("pass_price");
				Date passDate = rs.getDate("pass_date");
				int passStatus = rs.getInt("pass_status");
				int passMonth = rs.getInt("pass_month");
				int pauseCount = rs.getInt("pause_count");
				int puaseDate = rs.getInt("pause_date");
				String remarks = rs.getString("remarks");
				
				pass = new Pass();
				pass.setOwnerNo(ownerNo);
				pass.setPassNo(passNo);
				pass.setPassName(passName);
				pass.setPassPrice(passPrice);
				pass.setPassDate(passDate);
				pass.setPassStatus(passStatus);
				pass.setPassMonth(passMonth);
				pass.setPauseCount(pauseCount);
				pass.setPauseDate(puaseDate);
				pass.setRemarks(remarks);
				
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
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT owner_no, pass_no, pass_name, pass_price FROM pass WHERE owner_no = ? AND pass_status = 1";
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, ownerNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ownerNo = rs.getInt("owner_no");
				int passNo = rs.getInt("pass_no");
				String passName = rs.getString("pass_name");
				int passPrice = rs.getInt("pass_price");
				Pass pass = new Pass();
				pass.setOwnerNo(ownerNo);
				pass.setPassNo(passNo);
				pass.setPassName(passName);
				pass.setPassPrice(passPrice);
				
				passes.add(pass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return passes;
	}
	public static void main(String[] args) {
		try {
			System.out.println(dao.findPass(1, 1));
		} catch (FindException e) {
			e.printStackTrace();
		}
	}

}
