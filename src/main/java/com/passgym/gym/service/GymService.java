package com.passgym.gym.service;


import java.util.ArrayList;
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

GymDAOOracle gymDAO = new GymDAOOracle();
PassDAOOracle passDAO = new PassDAOOracle();
GymEquipDAOOracle gymEquipDAO = new GymEquipDAOOracle();

public void gymRegist(Gym gym, List<Pass> passes, List<GymEquip> gymEquips) throws AddException {
	gymDAO.add(gym);
	passDAO.add(passes);
	gymEquipDAO.add(gymEquips);
}

public Gym findGym(int ownerNo) throws FindException{
	Gym gym = gymDAO.findGymByOwnerNo(ownerNo);
		return gym;
	}
	
	public List<Gym> findByDistance(double latitude, double longitude) throws FindException{
		List<Gym> gymList = gymDAO.findByDistance(latitude, longitude);
		
		return gymList;
	}
	
	public List<Gym> findZzim(int userNo, double latitude, double longitude) throws FindException{
		List<Gym> zzimList = gymDAO.findZzim(userNo, latitude, longitude);
		
		return zzimList;
	}
	
}
