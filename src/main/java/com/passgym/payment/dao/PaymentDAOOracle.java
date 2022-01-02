package com.passgym.payment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.passgym.exception.AddException;
import com.passgym.payment.vo.Payment;
import com.passgym.sql.PassGymConnection;

public class PaymentDAOOracle implements PaymentDAOInterface{
	private static PaymentDAOOracle dao = new PaymentDAOOracle();
	private PaymentDAOOracle() {}
	public static PaymentDAOOracle getInstance() {
		return dao;
	}
	
	@Override
	public void addPayment(Payment p) throws AddException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String insertSQL = "INSERT INTO payment VALUES (?, ?, ?, ?, SYSDATE)";
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, p.getPaymentNo());
			pstmt.setInt(2, p.getPaymentPrice());;
			pstmt.setInt(3, p.getPaymentType());;
			pstmt.setString(4, p.getBankName());
			pstmt.executeUpdate();
			System.out.println("결제 성공");
		} catch (SQLException e) {
			//paymentNo가 중복된 경우 오라클 오류번호 1번 발생
			int errorCode = e.getErrorCode();
			if(errorCode == 1) {//ID가 중복된 경우
				throw new AddException("이미 존재하는 주문번호입니다");
			}else {
				e.printStackTrace();
				throw new AddException(e.getMessage());
			}
		} finally {
			PassGymConnection.close(pstmt, con);
		}
	}
	
//	public static void main(String[] args) {
////		PaymentDAOOracle dao = new PaymentDAOOracle();
////		Payment p = new Payment("5", 200000, 2, "농협은행");
////		try {
////			dao.addPayment(p);
////		} catch (AddException e) {
////			e.printStackTrace();
////		}
//	}


}