package com.passgym.equip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.passgym.equip.vo.Equip;
import com.passgym.exception.FindException;
import com.passgym.sql.PassGymConnection;

public class EquipDAOOracle implements EquipDAOInterface {

	@Override
	public List<Equip> findAll() throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Equip> equips = new ArrayList<Equip>();
		String selectSQL = "SELECT * FROM equip";
		
		try {
			con = PassGymConnection.getConnection();
			pstmt = con.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int equipNo = rs.getInt("equip_no");
				String equipName = rs.getString("equip_name");
				Equip equip = new Equip(equipNo, equipName);
				equips.add(equip);
			} 
			return equips;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException("등록된 장비가 없음");
		} finally {
			PassGymConnection.close(rs, pstmt, con);
		}
	}


}
