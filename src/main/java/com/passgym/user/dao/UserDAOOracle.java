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
import com.passgym.gym.vo.Gym;
import com.passgym.gympass.vo.GymPass;
import com.passgym.pass.vo.Pass;
import com.passgym.sql.PassGymConnection;
import com.passgym.star.vo.Star;
import com.passgym.user.vo.User;
import com.passgym.userqna.vo.UserQna;
import com.passgym.zzim.vo.Zzim;

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
				+ "(user_no, id, name, pwd, phone_no, zipcode, addr, addr_detail) \r\n" //, sns) \r\n"
				+ "VALUES (user_seq.nextval, ?, ?, ?, ?, ?, ?, ?)"; //, ?)";
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(insertSQL);
//			pstmt.setInt(1, user.getUserNo());//user_no도 String으로 바꿀건지?
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPwd());
			pstmt.setString(4, user.getPhoneNo());
			pstmt.setString(5, user.getZipcode());//user.vo에서 zipcode 자료형 String으로 변경 후 setInt > setString으로 바꿔줄 것
			pstmt.setString(6, user.getAddr());
			pstmt.setString(7, user.getAddrDetail());
			//pstmt.setString(8, user.getSns());
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
				String sns = "";
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
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = PassGymConnection.getConnection();
			String mypageSQL = "SELECT u.user_no , u.id, u.name u_name, u.zipcode u_zipcode, u.addr u_addr, u.addr_detail u_addr_detail, \r\n"
					+ "gp.payment_no, gp.owner_no gp_owner_no, gp.status gp_status, g1.name g1_name, \r\n"
					+ "p.pass_name pass_name, g1.total_star g1_total_star, g1.total_member g1_total_member, ROUND((g1.total_star / g1.total_member), 2) AS g1_avg_star, \r\n"
					+ "gp.start_date, gp.end_date, s.star,\r\n"
					+ "CASE WHEN end_date < SYSDATE THEN 0\r\n"
					+ "     WHEN start_date <= SYSDATE AND end_date >= SYSDATE THEN ROUND((gp.end_date - SYSDATE), 0)\r\n"
					+ "     ELSE -1\r\n"
					+ "END AS remain, -1 z_user_no, -1 z_owner_no, '' g2_name, '' g2_phone_no, '' g2_zipcode, '' g2_addr, '' g2_addr_detail,\r\n"
					+ "0 g2_total_star, 0 g2_total_member, 0 g2_avg_star,\r\n"
					+ "-1 qna_no, '' title, '' content, '' reply, -1 reply_status, null qna_date\r\n"
					+ "FROM user_info u LEFT OUTER JOIN gym_pass gp ON (u.user_no = gp.user_no)\r\n"
					+ "                 LEFT OUTER JOIN gym g1 ON (gp.owner_no = g1.owner_no)\r\n"
					+ "                 LEFT OUTER JOIN star s ON (gp.payment_no = s.payment_no)\r\n"
					+ "                 JOIN pass p ON (gp.owner_no = p.owner_no AND gp.pass_no = p.pass_no)\r\n"
					+ "WHERE u.user_no = ?\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT u.user_no , u.id, u.name, u.zipcode, u.addr, u.addr_detail, '', -1, -1,'', '', 0, 0, 0, null, null, 0,\r\n"
					+ "-2 , z.user_no, z.owner_no z_owner_no, g2.name g2_name, g2.phone_no g2_phone_no, g2.zipcode g2_zipcode, g2.addr g2_addr, g2.addr_detail g2_addr_detail,\r\n"
					+ "g2.total_star g2_total_star, g2.total_member g2_total_member, ROUND((g2.total_star / g2.total_member), 2) g2_avg_star,\r\n"
					+ "-1, '', '', '', -1, null\r\n"
					+ "FROM user_info u LEFT OUTER JOIN zzim z ON (u.user_no = z.user_no)\r\n"
					+ "                 LEFT OUTER JOIN gym g2 ON (z.owner_no = g2.owner_no)\r\n"
					+ "WHERE u.user_no = ?\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT u.user_no , u.id, u.name, u.zipcode, u.addr, u.addr_detail, '', -1, -1, '', '', 0, 0, 0, null, null, 0,\r\n"
					+ "-2, -1, -1, '', '', '', '', '', 0, 0, 0,\r\n"
					+ "uq.qna_no, uq.title, uq.content, uq.reply, uq.reply_status, uq.qna_date\r\n"
					+ "FROM user_info u LEFT OUTER JOIN user_qna uq ON (u.user_no = uq.user_no)\r\n"
					+ "WHERE u.user_no = ?";
			pstmt = con.prepareStatement(mypageSQL);
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, userNo);
			pstmt.setInt(3, userNo);
			rs = pstmt.executeQuery();
			
			User user = new User();
			List<GymPass> gympassList = new ArrayList<>();
			GymPass gympass;
			Pass pass;
			Gym gpGym;
			Star star;
			List<Zzim> zzimList = new ArrayList<>();
			Zzim zzim;
			Gym zGym;
			List<UserQna> userQnaList = new ArrayList<>();
			UserQna userQna;
			
			int count = 0;
			while(rs.next()) {
				//user
				if(count == 0) {
					user.setUserNo(rs.getInt("user_no"));
					user.setId(rs.getString("id"));
					user.setName(rs.getString("u_name"));
					user.setZipcode(rs.getString("u_zipcode"));
					user.setAddr(rs.getString("u_addr"));
					user.setAddrDetail(rs.getString("u_addr_detail"));
					gympassList = new ArrayList<>();
					count++;
				}

				//gympassList
				String payment_no = rs.getString("payment_no");
				if( payment_no != null) {
					gympass = new GymPass();
					gympass.setPaymentNo(payment_no);//set gympass -> gympassList에 추가
					pass = new Pass();
					pass.setOwnerNo(rs.getInt("gp_owner_no"));
					gpGym = new Gym();
					gpGym.setName(rs.getString("g1_name"));//gympass의 gym이름
					gpGym.setTotalStar(rs.getInt("g1_total_star"));
					gpGym.setTotalMember(rs.getInt("g1_total_member"));
					gpGym.setAvgStar(rs.getDouble("g1_avg_star"));
					pass.setGym(gpGym);
					pass.setPassName(rs.getString("pass_name"));
					gympass.setPass(pass);
					star = new Star();
					star.setStar(rs.getInt("star"));
					gympass.setStar(star);
					gympass.setStatus(rs.getInt("gp_status"));
					gympass.setStartDate(rs.getDate("start_date"));
					gympass.setEndDate(rs.getDate("end_date"));
					gympass.setRemain(rs.getInt("remain"));
					gympassList.add(gympass);
				}
				
				//zzimList
				int z_user_no = rs.getInt("z_user_no");
				if(z_user_no > 0) {
					zzim = new Zzim();
					zGym = new Gym();
					zGym.setOwnerNo(rs.getInt("z_owner_no"));
					zGym.setName(rs.getString("g2_name"));
					zGym.setPhoneNo(rs.getString("g2_phone_no"));
					zGym.setZipcode(rs.getString("g2_zipcode"));
					zGym.setAddr(rs.getString("g2_addr"));
					zGym.setAddrDetail(rs.getString("g2_addr_detail"));
					zGym.setTotalStar(rs.getInt("g2_total_star"));
					zGym.setTotalMember(rs.getInt("g2_total_member"));
					zGym.setAvgStar(rs.getDouble("g2_avg_star"));
					zzim.setGym(zGym);
					zzimList.add(zzim);
				}
				
				//userQna_list
				int qna_no = rs.getInt("qna_no");
				if(qna_no != -1) {
					userQna = new UserQna();
					userQna.setReplyStatus(qna_no);
					userQna.setTitle(rs.getString("title"));
					userQna.setContent(rs.getString("content"));
					userQna.setReply(rs.getString("reply"));
					userQna.setReplyStatus(rs.getInt("reply_status"));
					userQna.setQnaDate(rs.getDate("qna_date"));
					userQnaList.add(userQna);
				}
			}
			if(user.getUserNo() == -1) {
				throw new FindException("사용자정보가 존재하지 않습니다.");
			}
			user.setGymPasses(gympassList);
			user.setZzims(zzimList);
			user.setUserQnas(userQnaList);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException("해당값을 찾지 못했습니다.");
		}finally {
			PassGymConnection.close(rs, pstmt, con);
		}
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
	
	public User findByPhoneNo(String phoneNo) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User u = null;
		String selectSQL = "SELECT * FROM user_info WHERE phone_no = ?";
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, phoneNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {//아이디는 단일값이니 while이 아닌 if문
				int userNo = rs.getInt("user_no");
				phoneNo = rs.getString("phoneNo");
				String id = rs.getString("id");
				String name = rs.getString("name");				
				String pwd = rs.getString("pwd");
				String zipcode = rs.getString("zipcode");//user.vo에서 zipcode 자료형 String으로 변경 후 setInt > setString으로 바꿔줄 것
				String addr = rs.getString("addr");
				String addrDetail = rs.getString("addr_detail");
				String sns = "";
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
	
	public static void main(String[] args) {


		UserDAOOracle dao = UserDAOOracle.getInstance();
		User u = new User();
		u.setId("tid");
		u.setPwd("tpwd");
		u.setName("tname");
		u.setPhoneNo("tphone_no");
		u.setZipcode("tzipc");
		u.setAddr("taddr1");
		u.setAddrDetail("taddr2"); 	
		try {
			dao.addUser(u);
		} catch (AddException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

//		UserDAOOracle dao = UserDAOOracle.getInstance();
//		try {
//			User user = dao.mypageFindByNo(2);
//			System.out.println(user);
//			for(GymPass gp: user.getGymPasses()) {
//				System.out.println(gp);
//				System.out.println(gp.getPass());
//				System.out.println(gp.getPass().getGym());
//				System.out.println(gp.getStar());
//			}
//			for(Zzim z : user.getZzims()) {
//				System.out.println(z);
//				System.out.println(z.getGym());
//			}
//			for(UserQna uq : user.getUserQnas()) {
//				System.out.println(uq);
//			}
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
	}
}
