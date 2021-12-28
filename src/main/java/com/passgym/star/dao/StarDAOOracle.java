package com.passgym.star.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.passgym.exception.AddException;
import com.passgym.sql.PassGymConnection;
import com.passgym.star.vo.Star;

public class StarDAOOracle implements StarDAOInterface {
	private static StarDAOOracle dao = new StarDAOOracle();
	
	private StarDAOOracle() {};
	
	public static StarDAOOracle getInstance() {
		return dao;
	}

	@Override
	public void addStar(Star star) throws AddException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String addStarSQL = "INSERT INTO star VALUES(?, ?)";
		
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(addStarSQL);
			pstmt.setString(1, star.getPaymentNo());
			pstmt.setInt(2, star.getStar());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException("별점이 추가되지 못했습니다.");
		}
	}
	
	public static void main(String[] args) {
//		StarDAOOracle dao = StarDAOOracle.getInstance();
//		Star star = new Star("3", 3);
//		try {
//			dao.addStar(star);
//		} catch (AddException e) {
//			e.printStackTrace();
//		}
	}
}
