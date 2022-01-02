package com.passgym.equip.service;

import java.util.List;

import com.passgym.equip.dao.EquipDAOOracle;
import com.passgym.equip.vo.Equip;
import com.passgym.exception.FindException;

public class EquipService {
	EquipDAOOracle equipDAO = EquipDAOOracle.getInstance();
	
	public List<Equip> findEquipList() throws FindException{
		List<Equip> equips = null;
	
		equips = equipDAO.findAll();

		return equips;
	}
}
