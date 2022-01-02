package com.passgym.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.gym.vo.Gym;
import com.passgym.gympass.vo.GymPass;
import com.passgym.pass.vo.Pass;
import com.passgym.payment.vo.Payment;
import com.passgym.sql.PassGymConnection;
import com.passgym.user.vo.User;
import com.passgym.zzim.vo.Zzim;

public class GymDAOOracle implements GymDAOInterface {
	private static GymDAOOracle dao = new GymDAOOracle();
	public GymDAOOracle() {}
	public static GymDAOOracle getInstance() {
		return dao;
	}

	@Override
	public List<Pass> findByOwnerNo(int ownerNo) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {
			con = PassGymConnection.getConnection();
			String selectPassByOwnerNo = "select \r\n"
					+ "pass.pass_no, pass_name, pass_price, pass_date, pass_status, pass_month, pass.pause_count, pass.pause_date, remarks,\r\n"
					+ "g.user_no, g.start_date, g.end_date, g.status, g.pause_count g_pause_count, g.pause_date g_pause_date,\r\n"
					+ "u.id, u.name, u.phone_no ,  u.addr, u.addr_detail, \r\n"
					+ "p.payment_no, p.payment_price, p.payment_type, p.bank_name, p.payment_date    \r\n"
					+ "from pass \r\n"
					+ "left outer join gym_pass g on (pass.owner_no = g.owner_no AND pass.pass_no = g.pass_no)\r\n"
					+ "left outer join user_info u on g.user_no = u.user_no\r\n"
					+ "left outer join payment p on g.payment_no = p.payment_no\r\n"
					+ "where pass.owner_no = ?            -- 회원권 상태 (사용가능 1, 사용불가능 0)\r\n"
					+ "order by pass.pass_no, u.user_no";
			pstmt = con.prepareStatement(selectPassByOwnerNo);
			pstmt.setInt(1, ownerNo);
			rs = pstmt.executeQuery();
			/*                                                  gym_pass
		pass                                                user_nostart_dateend_date status g_pause_count g_pause_date           user_info
1	크리스마스    	100000	21/12/17	1	1	0	0	 1개월권															
2	방학특가 이용권	250000	21/12/23	0	3	2	30	 3개월권	1	21/12/17	22/03/16	1	    2	         30	            id1	tname1	01011112222	경기도 수원시 팔달구 지동 279-1	포레스트 311호	2	250000	신한카드	21/12/23
2	방학특가 이용권	250000	21/12/23	0	3	2	30	 3개월권	2	21/01/17	22/04/16	0	0	0	                        id2	tname2	   01022223333	서울특별시 강남구 테헤란로 30-2	현대 오피스텔 201호	1	250000	농협카드	21/01/17
2	방학특가 이용권	250000	21/12/23	0	3	2	30	 3개월권	5	21/12/22	22/03/22	1	2	             30	            id4	tname4	01033334455	부산 해운대구 마린시티3로 23  	오렌지상가 2층 220호	5	500000	하나카드	21/12/23			 */
			
			//
			List<Pass> passes = new ArrayList<>();
			Pass pass = null;
			List<GymPass> gympasses = null;  
			
			 
			
			int oldPassNo = -1; //이전행의 회원권번호 
			while(rs.next()) {  //회원권(pass)이용권에 관한 기본정보
				int passNo = rs.getInt("pass_no");
				String passName = rs.getString("pass_name");
				int passPrice = rs.getInt("pass_price");
				Date passDate = rs.getDate("pass_date");
				int passStatus = rs.getInt("pass_status");
				int passMonth = rs.getInt("pass_month");
				int pauseCount = rs.getInt("pause_count");
				int pauseDate = rs.getInt("pause_date");
				String remarks = rs.getString("remarks");
				
				if(oldPassNo != passNo) { //이전행의 회원권번호와 현재행의 회원권번호가 다르면 생성
					pass = new Pass();  
					pass.setPassNo(ownerNo);
					pass.setPassName(passName);
					pass.setPassPrice(passPrice);
					pass.setPassDate(passDate);
					pass.setPassStatus(passStatus);
					pass.setPassMonth(passMonth);
					pass.setPauseCount(pauseCount);
					pass.setPauseDate(pauseDate);
					
					gympasses = new ArrayList<>();  //?
					pass.setGympasses(gympasses);   ///  ??
					
					passes.add(pass);
					oldPassNo = passNo;
				}
				GymPass gp = new GymPass();
				User u = new User();
//				u.id, u.name, u.phone_no ,  u.addr, u.addr_detail
				u.setUserNo(rs.getInt("user_no"));		
				u.setId(rs.getString("id"));
				u.setName(rs.getString("name"));
				u.setPhoneNo(rs.getString("phone_no"));
				u.setAddr(rs.getString("addr"));
				u.setAddrDetail(rs.getString("addr_detail"));
				gp.setUser(u);
				
				//user_nostart_dateend_date status g_pause_count g_pause_date
				
				gp.setStartDate(rs.getDate("start_date"));
				gp.setEndDate(rs.getDate("end_date"));
				gp.setStatus(rs.getInt("status"));
				gp.setPauseCount(rs.getInt("g_pause_count"));
				gp.setPauseDate(rs.getInt("g_pause_date"));
				
				
//				p.payment_no, p.payment_price, p.bank_name, p.payment_date  
				Payment pay = new Payment();
				pay.setPaymentNo(rs.getString("payment_no"));
				pay.setPaymentPrice(rs.getInt("payment_price"));
				pay.setBankName(rs.getString("bank_name"));
				pay.setPaymentDate(rs.getDate("payment_date"));
				gp.setPayment(pay);
				gympasses.add(gp);
			}
			if(passes.size() == 0) {
				throw new FindException("이용권이 없습니다.");
			}
			
			return passes;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			PassGymConnection.close(rs, pstmt,con);
		}
		
	}
	
	@Override
	public List<Gym> findByDistance(double latitude, double longitude) throws FindException {
		List<Gym> gymList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT owner_no, name, addr, ROUND((total_star / total_member), 2) AS avg_star,\r\n"
				+ "NVL((POWER(total_star, 7) / POWER(total_member, 6)), 0) AS best, DISTANCE_WGS84(?, ?, lat, lon) AS distance\r\n"
				+ "FROM gym\r\n"
				+ "ORDER BY distance, best DESC";
		if(latitude == 0.0 && longitude == 0.0) {
			latitude = 37.554837;
			longitude = 126.971732;
		}
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setDouble(1, latitude);
			pstmt.setDouble(2, longitude);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int ownerNo = rs.getInt("owner_no");
				String Name = rs.getString("name");
				String addr = rs.getString("addr");
				double distance = rs.getDouble("distance");
				double avgStar = rs.getDouble("avg_star");
				Gym g = new Gym(ownerNo, Name, null, null, addr, null, null, null, null, null, null, null, 0, 0, avgStar, 0, 0, distance);
				
				gymList.add(g);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			PassGymConnection.close(rs, pstmt, con);
		}
		
		return gymList;
	}
	
	
	public static void main(String[] args) {
		
		GymDAOOracle dao = new GymDAOOracle();
//		Pass pass  = new Pass();
		try {
			int ownerNo = 1;
			List<Pass> passes = dao.findByOwnerNo(ownerNo);
			System.out.println(ownerNo + "이용권 종류 : "+ passes.size());
			for(Pass p: passes) {
				System.out.println("<이용권 정보>");
				System.out.println(p);
				System.out.println("-----헬스장 이용권 구매한 회원 내역 --------");
				System.out.println("id : name : paymentNo :paymentPrice");
				for(GymPass gp: p.getGympasses()) {
					User u = gp.getUser();
					Payment pay = gp.getPayment();
					System.out.println(u.getId() + ":" + u.getName() + ":" + pay.getPaymentNo() + ":" + pay.getPaymentPrice());
				}
				System.out.println("-----------------------");
				
				
			}
		} catch (FindException e) {
			e.printStackTrace();
		}
		
	 
		 
	}

	@Override
	public void add(Gym gym) throws AddException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String updateSQL = "UPDATE gym SET introduce=?, \r\n"
				+ "                notice=?, \r\n"
				+ "                operating_time=?, \r\n"
				+ "                operating_program=?,\r\n"
				+ "                extra_service=?,\r\n"
				+ "                etc=?"
				+ "				   WHERE owner_no=?";
		
//		String insertSQL = "INSERT INTO gym(owner_no, name, phone_no, zipcode, addr, addr_detail, introduce, notice, \r\n"
//				+ "operating_time, operating_program, extra_service, etc, total_star, total_member, lat, lon)\r\n"
//				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0, ?, ?)";
		
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(updateSQL);
			pstmt.setString(1, gym.getIntroduce());
			pstmt.setString(2, gym.getNotice());
			pstmt.setString(3, gym.getOperatingTime());
			pstmt.setString(4, gym.getOperatingProgram());
			pstmt.setString(5, gym.getExtraService());
			pstmt.setString(6, gym.getEtc());
			pstmt.setInt(7, gym.getOwnerNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			e.printStackTrace();
		}finally {
			PassGymConnection.close(pstmt, con);
		}
	}



	@Override
	public void signupAdd(Gym gym) throws AddException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String insertSQL = "INSERT INTO gym(owner_no, name, phone_no, zipcode, addr, addr_detail, \r\n"
				+ "lat, lon)\r\n"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setInt(1, gym.getOwnerNo());
			pstmt.setString(2, gym.getName());
			pstmt.setString(3, gym.getPhoneNo());
			pstmt.setString(4, gym.getZipcode());
			pstmt.setString(5, gym.getAddr());
			pstmt.setString(6, gym.getAddrDetail());
			pstmt.setDouble(7, gym.getLat());
			pstmt.setDouble(8, gym.getLon());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			System.out.println(errorCode);
			e.printStackTrace();
		}finally {
			PassGymConnection.close(pstmt, con);
		}
		
		
	}



	@Override
	public Gym findGymByOwnerNo(int ownerNo) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT * FROM gym WHERE owner_no = ?";
		Gym gym = null;
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, ownerNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int findOwnerNo = rs.getInt("owner_no");
				String name = rs.getString("name");
				String phoneNo = rs.getString("phone_no");
				String zipCode = rs.getString("zipcode");
				String addr = rs.getString("addr");
				String addrDetail = rs.getString("addr_detail");
				double lat = rs.getDouble("lat");
				double lon = rs.getDouble("lon");
				gym = new Gym(ownerNo, name, phoneNo, zipCode, 
						addr, addrDetail, null, null, null, null, null, null, 0, 0, 0, lat, lon, 0);
			}

			return gym;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			PassGymConnection.close(pstmt, con);
		}
		
		
	}

	@Override
	public List<Gym> findZzim(int userNo, double latitude, double longitude) throws FindException {
		//ZzimDAOOracle dao = new ZzimDAOOracle();
		List<Gym> gymList = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		if(latitude == 0.0 && longitude == 0.0) {
			latitude = 37.554837;
			longitude = 126.971732;
		}
		try {
			con = PassGymConnection.getConnection();
			String selectSQL = "SELECT g.owner_no, name, g.addr, ROUND((g.total_star / g.total_member), 2) AS avg_star,\r\n"
					+ "NVL((POWER(g.total_star, 7) / POWER(g.total_member, 6)), 0) AS best, DISTANCE_WGS84(" + latitude + ", "+ longitude + ", lat, lon) AS distance\r\n"
					+ "FROM gym g JOIN zzim z ON g.owner_no = z.owner_no\r\n"
					+ "WHERE z.user_no = " + userNo + "\r\n"
					+ "ORDER BY distance, best DESC";
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectSQL);
			if(rs.next()) {
				while(rs.next()) {
					int ownerNo = rs.getInt("owner_no");
					String gymName = rs.getString("name");
					String gymAddr = rs.getString("addr");
					double distance = rs.getDouble("distance");
					double avgStar = rs.getDouble("avg_star");
					Gym g = new Gym(ownerNo, gymName, null, null, gymAddr, null, null, null, null, null, null, null, 0, 0, avgStar, 0, 0, distance);
					
					gymList.add(g);
				}
			}else {
				throw new FindException("찜한 헬스장이 없습니다");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			PassGymConnection.close(rs, stmt, con);
		}
		return gymList;
	}
	@Override
	public Gym gymDetail(int ownerNo) throws FindException {//헬스장 상세페이지용
		Gym g = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT * FROM gym WHERE owner_no = ?";
		
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, ownerNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ownerNo = rs.getInt("owner_no");//미완성
				String name = rs.getString("name");
				String phoneNo = rs.getString("phone_no");
				String zipcode = rs.getString("zipcode");
				String addr = rs.getString("addr");
				String addrDetail = rs.getString("addr_detail");
				String introduce = rs.getString("introduce");
				String notice = rs.getString("notice");
				String operatingTime = rs.getString("operating_time");
				String operatingProgram = rs.getString("operating_program");
				String extraService = rs.getString("extra_service");
				String etc = rs.getString("etc");
				double distance = rs.getDouble("distance");
				double avgStar = rs.getDouble("avg_star");
				
				g.setOwnerNo(ownerNo);
				g.setName(name);
				g.setPhoneNo(phoneNo);
				g.setZipcode(zipcode);
				g.setAddr(addr);
				g.setAddrDetail(addrDetail);
				g.setIntroduce(introduce);
				g.setNotice(notice);
				g.setOperatingTime(operatingTime);
				g.setOperatingProgram(operatingProgram);
				g.setExtraService(extraService);
				g.setEtc(etc);
				g.setDistance(distance);
				g.setAvgStar(avgStar);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			PassGymConnection.close(rs, pstmt, con);
		}
		return g;
		
	}
}