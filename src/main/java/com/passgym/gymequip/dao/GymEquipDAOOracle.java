package com.passgym.gymequip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.passgym.equip.dao.EquipDAOOracle;
import com.passgym.exception.AddException;
import com.passgym.gymequip.vo.GymEquip;
import com.passgym.sql.PassGymConnection;

public class GymEquipDAOOracle implements GymEquipDAOInterface {
	private static GymEquipDAOOracle dao = new GymEquipDAOOracle();
	private GymEquipDAOOracle() {}
	public static GymEquipDAOOracle getInstance() {
		return dao;
	}
	
	@Override
	public void add(List<GymEquip> gymEquips) throws AddException{
		Connection con = null;
		PreparedStatement pstmt = null;
		String insertSQL = "INSERT INTO gym_equip(owner_no, equip_no, equip_count) "
				+ "VALUES (?, ?, ?)";
		
			try {
				con = PassGymConnection.getConnection();
				pstmt = con.prepareStatement(insertSQL);
				for(GymEquip gymEquip: gymEquips) {
					pstmt.setInt(1, gymEquip.getOwnerNo());
					pstmt.setInt(2, gymEquip.getEquipNo());
					pstmt.setInt(3, gymEquip.getEquipCount());
					pstmt.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				PassGymConnection.close(pstmt, con);
			}
		
	}

}
