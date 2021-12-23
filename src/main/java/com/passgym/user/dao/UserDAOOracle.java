package com.passgym.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.exception.ModifyException;
import com.passgym.gympass.vo.GymPass;
import com.passgym.sql.PassGymConnection;
import com.passgym.user.vo.User;

public class UserDAOOracle implements UserDAOInterface {
	private static UserDAOOracle dao = new UserDAOOracle();
	private UserDAOOracle() {
	}
	public static UserDAOOracle getInstance() {
		return dao;
	}

	@Override
	public void addUser(User user) throws AddException {//사용자 추가하기(회원가입)
		Connection con = null;
		PreparedStatement pstmt = null;
		String insertSQL = 
				"INSERT INTO user_info\r\n"
				+ "(user_no, id, name, pwd, phone_no, zipcode, addr, addr_detail, sns) \r\n"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setInt(1, user.getUserNo());//user_no도 String으로 바꿀건지?
			pstmt.setString(2, user.getId());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getPwd());
			pstmt.setString(5, user.getPhoneNo());
			pstmt.setString(6, user.getZipcode());//user.vo에서 zipcode 자료형 String으로 변경 후 setInt > setString으로 바꿔줄 것
			pstmt.setString(7, user.getAddr());
			pstmt.setString(8, user.getAddrDetail());
			pstmt.setString(9, user.getSns());
			pstmt.executeUpdate();//바인드 변수들 setting 후 DB로 송신
			System.out.println("ID 추가 성공");//console test용
		} catch (SQLException e) {
			//ID가 중복된 경우 오라클 오류번호 1번 발생
			int errorCode = e.getErrorCode();
			if(errorCode == 1) {//ID가 중복된 경우
				throw new AddException("이미 존재하는 아이디입니다");
			}else {
				e.printStackTrace();
				throw new AddException(e.getMessage());
			}
		} finally {
			PassGymConnection.close(pstmt, con);
		}
	}

	@Override
	public User findByUserId(String id) throws FindException {//사용자 아이디로 사용자 찾기
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User u = null;
		String selectSQL = "SELECT * FROM user_info WHERE id = ?";
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {//아이디는 단일값이니 while이 아닌 if문
				int userNo = rs.getInt("user_no");
				id = rs.getString("id");
				String name = rs.getString("name");
				String pwd = rs.getString("pwd");
				String phoneNo = rs.getString("phone_no");
				String zipcode = rs.getString("zipcode");//user.vo에서 zipcode 자료형 String으로 변경 후 setInt > setString으로 바꿔줄 것
				String addr = rs.getString("addr");
				String addrDetail = rs.getString("addr_detail");
				String sns = rs.getString("sns");
				u = new User(userNo, id, name, pwd, phoneNo, zipcode, addr, addrDetail, sns);
			}else {
				throw new FindException("아이디에 해당하는 사용자가 없습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			PassGymConnection.close(rs, pstmt, con);
		}
		return u;//사용자 아이디에 해당하는 사용자객체 반환
	}

	@Override
	public User findByUserNo(int userNo) throws FindException {//사용자 번호로 사용자 찾기
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User u = null;
		String selectSQL = "SELECT * FROM user_info WHERE user_no = ?";
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, userNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {//사용자번호는 단일값이니 while이 아닌 if문
				userNo = rs.getInt("user_no");
				String id = rs.getString("id");
				String name = rs.getString("name");
				String pwd = rs.getString("pwd");
				String phoneNo = rs.getString("phone_no");
				String zipcode = rs.getString("zipcode");//user.vo에서 zipcode 자료형 String으로 변경 후 setInt > setString으로 바꿔줄 것
				String addr = rs.getString("addr");
				String addrDetail = rs.getString("addr_detail");
				String sns = rs.getString("sns");
				u = new User(userNo, id, name, pwd, phoneNo, zipcode, addr, addrDetail, sns);
			}else {
				throw new FindException("사용자번호에 해당하는 사용자가 없습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			PassGymConnection.close(rs, pstmt, con);
		}
		return u;//사용자 번호에 해당하는 사용자객체 반환
	}
	
	@Override
	public User mypageFindByNo(int userNo) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<User> findByUserName(String word) throws FindException {//단어를 포함하는 이름으로 사용자 찾기
		List<User> userList = new ArrayList<>();//word로 찾은 User객체를 list에 담기
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		User u = null;
		try {
			con = PassGymConnection.getConnection();
			String selectSQL = "SELECT * FROM user_info WHERE name LIKE '%" + word + "%'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectSQL);
			while(rs.next()) {//단어를 포함한 이름을 가진 사용자가 여럿일 수 있으니 while문 사용
				int userNo = rs.getInt("user_no");
				String id = rs.getString("id");
				String name = rs.getString("name");
				String pwd = rs.getString("pwd");
				String phoneNo = rs.getString("phone_no");
				String zipcode = rs.getString("zipcode");//user.vo에서 zipcode 자료형 String으로 변경 후 setInt > setString으로 바꿔줄 것
				String addr = rs.getString("addr");
				String addrDetail = rs.getString("addr_detail");
				String sns = rs.getString("sns");
				u = new User(userNo, id, name, pwd, phoneNo, zipcode, addr, addrDetail, sns);
				userList.add(u);
			}
			if(userList.size() == 0) {//userList에 담긴 사용자가 없을 때
				throw new FindException("단어를 포함한 이름의 사용자가 없습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			PassGymConnection.close(rs, stmt, con);
		}
		return userList;//단어를 포함한 사용자객체를 List로 반환
	}

	@Override
	public String findUserId(String name, String phoneNo) throws FindException {//이름과 휴대폰 번호로 사용자의 Id찾기 기능
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String userId = null;
		try {
			con = PassGymConnection.getConnection();
			String selectSQL =
					"SELECT id FROM user_info WHERE name = '" + name + "' AND phone_no = '" + phoneNo + "'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectSQL);
			if(rs.next()) {//이름과 휴대폰 번호가 일치하는 고객은 1명이니 if문
				userId = rs.getString("id");
			}else {//이름 또는 휴대폰 번호가 일치하지 않으면 예외 발생
				throw new FindException("해당 이름 또는 휴대폰 번호를 가진 사용자가 없습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			PassGymConnection.close(rs, stmt, con);
		}
		
		return userId;//해당하는 사용자가 있다면 Id값 반환
	}

	@Override
	public List<GymPass> findGymByUserNo(int userNo) throws FindException {//사용자 번호로 결제한 이용권 리스트 찾기
		List<GymPass> gymPassList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT * FROM gym_pass WHERE user_no = ?";
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, userNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int paymentNo = rs.getInt("payment_no");
				int ownerNo = rs.getInt("owner_no");
				int passNo = rs.getInt("pass_no");
				userNo = rs.getInt("user_no");
				java.util.Date startDate = rs.getDate("start_date");
				java.util.Date endDate = rs.getDate("end_date");
				int pauseCount = rs.getInt("pause_count");
				int pauseDate = rs.getInt("pause_date");
				int status = rs.getInt("status");
				//GymPass gymPass = new GymPass(paymentNo, ownerNo, passNo, userNo ,startDate, endDate, pauseCount, pauseDate, status);
				//gymPassList.add(gymPass);
			}
			if(gymPassList.size() == 0) {
				throw new FindException("결제한 헬스장이 존재하지 않습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			PassGymConnection.close(rs, pstmt, con);
		}
		return gymPassList;
	}

	@Override
	public void modifyUserPwd(String pwd) throws ModifyException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String updateSQL = "UPDATE user_info SET pwd = ?";
		try {
			dao.findByUserId(updateSQL);
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(updateSQL);
			pstmt.setString(1, pwd);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FindException e) {
			e.printStackTrace();
		}
		
	}

	@Override//user.vo에서 zipcode 자료형 String으로 변경 후 setInt > setString으로 바꿔줄 것
	public void modifyUserAddr(int zipcode, String addr, String addrDetail) throws ModifyException {
		
	}
	public static void main(String[] args) {
//		User u = new User(7, "id7", "name7", "pwd7", "01077777777", 17787, "서울특별시 관악구 남부순환로 218길", "777호", "");
//		try {//사용자 추가하기
//			dao.addUser(u);
//		} catch (AddException e) {
//			e.printStackTrace();
//		}
//		try {사용자 아이디로 사용자 찾기
//			System.out.println(dao.findByUserId("id5"));
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
//		try {사용자 번호로 사용자 찾기
//			System.out.println(dao.findByUserNo(2));
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
//		try {//이름에 해당 단어를 포함한 사용자 찾기
//			System.out.println(dao.findByUserName("t"));
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
//		try {//아이디 찾기
//			System.out.println(dao.findUserId("tname1", "01011112222"));
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
//		try {//사용자가 결제한 헬스장 목록 보여주기
//			System.out.println(dao.findGymByUserNo(2));
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
	}
}
