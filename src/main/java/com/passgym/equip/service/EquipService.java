package com.passgym.equip.service;

import java.util.List;

import com.passgym.equip.dao.EquipDAOOracle;
import com.passgym.equip.vo.Equip;
import com.passgym.exception.FindException;

public class EquipService {
	EquipDAOOracle equipDAO;
	
	public List<Equip> findEquipList() throws FindException{
		equipDAO = new EquipDAOOracle();
		List<Equip> equips = null;
	
		equips = equipDAO.findAll();

		return equips;
	}
}
