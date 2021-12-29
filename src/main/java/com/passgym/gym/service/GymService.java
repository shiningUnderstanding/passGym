package com.passgym.gym.service;

import java.util.List;

import com.passgym.equip.dao.EquipDAOOracle;
import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.gym.dao.GymDAOOracle;
import com.passgym.gym.vo.Gym;
import com.passgym.gymequip.dao.GymEquipDAOOracle;
import com.passgym.gymequip.vo.GymEquip;
import com.passgym.pass.dao.PassDAOOracle;
import com.passgym.pass.vo.Pass;

public class GymService {
	GymDAOOracle gymDAO;
	PassDAOOracle passDAO;
	GymEquipDAOOracle gymEquipDAO;
	
	public void gymRegist(Gym gym, List<Pass> passes, List<GymEquip> gymEquips) throws AddException {
		gymDAO = new GymDAOOracle();
		passDAO = new PassDAOOracle();
		gymEquipDAO = new GymEquipDAOOracle();
		gymDAO.add(gym);
		passDAO.add(passes);
		gymEquipDAO.add(gymEquips);
	}
	
	public Gym findGym(int ownerNo) throws FindException{
		gymDAO = new GymDAOOracle();
		Gym gym = gymDAO.findGymByOwnerNo(ownerNo);
		return gym;
	}
}
