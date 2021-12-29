package com.passgym.gympass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.passgym.exception.AddException;
import com.passgym.gympass.vo.GymPass;
import com.passgym.pass.vo.Pass;
import com.passgym.sql.PassGymConnection;
import com.passgym.user.dao.UserDAOOracle;
import com.passgym.user.vo.User;

public class GymPassDAOOracle implements GymPassDAOInterface {

	@Override
	public void addGymPass(GymPass gp) throws AddException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String insertSQL = "INSERT INTO gym_pass VALUES('?', ?, ?, ?, '?', '?', ?, ?, ?)";
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, gp.getPaymentNo());
			pstmt.setInt(2, gp.getPass().getOwnerNo());
			pstmt.setInt(3, gp.getPass().getPassNo());
			pstmt.setInt(4, gp.getUser().getUserNo());
			pstmt.setDate(5, (java.sql.Date)gp.getStartDate());
			pstmt.setDate(6, (java.sql.Date)gp.getEndDate());
			pstmt.setInt(7, gp.getPass().getPauseCount());
			pstmt.setInt(8, gp.getPass().getPauseDate());
			pstmt.setInt(9, gp.getStatus());
			pstmt.executeUpdate();
			System.out.println("이용권 등록 성공");
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if(errorCode == 1) {
				throw new AddException("이미 해당 결제번호의 사용권이 있습니다");
			}else {
				e.printStackTrace();
				throw new AddException(e.getMessage());
			}
		} finally {
			PassGymConnection.close(pstmt, con);
		}
		
	
	}
//	public static void main(String[] args) {
////		GymPassDAOOracle dao = new GymPassDAOOracle();
////		UserDAOOracle daoU = new UserDAOOracle();
////		Calendar cal = Calendar.getInstance();
////        cal.setTime(new Date());
////        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
////        Date startDate = null;
////        Date endDate = null;
////        Date now = Calendar.getInstance().getTime();
////		Pass p = null;
////		p.setOwnerNo(1);
////		p.setPassNo(1);
////        p.setPassDate(now);
////        p = new Pass(1, 1, "1개월권", 100000, now, 1, 1, 0, 0, "기본 1개월권");
////		//User u = new User();
////		User u = daoU.findByUserNo(1);
////        startDate = df.parse("2021-12-20");
////        cal.setTime(startDate);
////        cal.add(Calendar.MONTH, p.getPassMonth());
////        int remain = (startDate - cal.getTime()) / 1000;
////		GymPass gp = new GymPass("5", p, u, startDate, cal.getTime(), p.getPauseCount(), p.getPauseDate(), 1, );
//	}

}
