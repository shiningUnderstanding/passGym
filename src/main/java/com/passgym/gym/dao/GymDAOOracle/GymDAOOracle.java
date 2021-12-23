package com.passgym.gym.dao.GymDAOOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.passgym.exception.FindException;
import com.passgym.pass.vo.Pass;
import com.passgym.sql.PassGymConnection;

public class GymDAOOracle implements GymDAOInterface {

	@Override
	public List<Pass> findByOwnerNo(int ownerNo) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = PassGymConnection.getConnection();
			String selectPassByOwnerNo = "select u.id, u.name, u.phone_no , pass.*, u.addr, u.addr_detail, p.payment_no, p.payment_price, p.bank_name, p.payment_date, g.user_no, g.start_date, g.end_date, g.status    \r\n"
					+ "from pass pass \r\n"
					+ "left outer join gym_pass g on (pass.owner_no = g.owner_no AND pass.pass_no = g.pass_no)\r\n"
					+ "left outer join user_info u on g.user_no = u.user_no\r\n"
					+ "left outer join payment p on g.payment_no = p.payment_no\r\n"
					+ "where pass.owner_no = ?            -- 회원권 상태 (사용가능 1, 사용불가능 0)\r\n"
					+ "order by pass.pass_date, u.user_no";
			pstmt = con.prepareStatement(selectPassByOwnerNo);
			pstmt.setInt(1, ownerNo);
			rs = pstmt.executeQuery();
			/*
null null		null	1	1	크리스마스 특가 이용권	100000	21/12/17	1	1	0	0	 1개월권										
id1	tname1	01011112222	1	2	방학특가 이용권	250000	21/12/23	0	3	2	30	 3개월권	경기도 수원시 팔달구 지동 279-1	포레스트 311호	2	250000	신한카드	21/12/23	1	21/12/17	22/03/16	1
id2	tname2	01022223333	1	2	방학특가 이용권	250000	21/12/23	0	3	2	30	 3개월권	서울특별시 강남구 테헤란로 30-2	현대 오피스텔 201호	1	250000	농협카드	21/01/17	2	21/01/17	22/04/16	0
id4	tname4	01033334455	1	2	방학특가 이용권	250000	21/12/23	0	3	2	30	 3개월권	부산 해운대구 마린시티3로 23  	오렌지상가 2층 220호	5	500000	하나카드	21/12/23	5	21/12/22	22/03/22	1 
			 */
			List<Pass> passes = new ArrayList<>();
			int oldPassNo = -1;
			while(rs.next()) {
				int passNo = rs.getInt("pass_no");
				if(oldPassNo != passNo) {
					
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
