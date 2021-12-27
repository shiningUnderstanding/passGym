package com.passgym.gym.dao.GymDAOOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import com.passgym.exception.AddException;
import com.passgym.sql.PassGymConnection;
import com.passgym.exception.FindException;
import com.passgym.gym.vo.Gym;
import com.passgym.gympass.vo.GymPass;
import com.passgym.pass.vo.Pass;
import com.passgym.payment.vo.Payment;
 
import com.passgym.user.vo.User;

public class GymDAOOracle implements GymDAOInterface {

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
					
					pass.setGympasses(gympasses);   ///  ??
					gympasses = new ArrayList<>();  //?
					
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
		List<Gym> gyms = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT g.owner_no, g.name, g.addr, ROUND((g.total_star / g.total_member), 2) AS avg_star,\r\n"
				+ "NVL((POWER(g.total_star, 7) / POWER(g.total_member, 6)), 0) AS best, DISTANCE_WGS84(?, ?, g.lat, g.lon) AS distance\r\n"
				+ "FROM gym g\r\n"
				+ "ORDER BY distance, best DESC";
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setDouble(1, latitude);
			pstmt.setDouble(2, longitude);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int ownerNo = rs.getInt("owner_no");
				String gymName = rs.getString("name");
				String gymAddr = rs.getString("addr");
				double distance = rs.getDouble("distance");
				double avgStar = rs.getDouble("avg_star");
				Gym g = new Gym(ownerNo, gymName, gymAddr, distance, avgStar);
				gyms.add(g);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return gyms;
	}
	

	@Override
	public double printAvgStar(int ownerNo) throws FindException {
		// TODO Auto-generated method stub
		return 0;
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
		String insertSQL = "INSERT INTO gym(owner_no, name, phone_no, zipcode, addr, addr_detail, introduce, notice, \r\n"
				+ "operating_time, operating_program, extra_service, etc, total_star, total_member, lat, lon)\r\n"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0, ?, ?)";
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setInt(1, gym.getOwnerNo());
			pstmt.setString(2, gym.getName());
			pstmt.setString(3, gym.getPhoneNo());
			pstmt.setString(4, gym.getZipcode());
			pstmt.setString(5, gym.getAddr());
			pstmt.setString(6, gym.getAddrDetail());
			pstmt.setString(7, gym.getIntroduce());
			pstmt.setString(8, gym.getNotice());
			pstmt.setString(9, gym.getOperatingTime());
			pstmt.setString(10, gym.getOperatingProgram());
			pstmt.setString(11, gym.getExtraService());
			pstmt.setString(12, gym.getEtc());
			pstmt.setDouble(15, gym.getLat());
			pstmt.setDouble(16, gym.getLon());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			
		}finally {
			PassGymConnection.close(pstmt, con);
		}
	}
}