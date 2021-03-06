package com.passgym.gym.service;


import java.util.ArrayList;
import java.util.List;
import com.passgym.equip.dao.EquipDAOOracle;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.gym.dao.GymDAOOracle;
import com.passgym.gym.vo.Gym;
import com.passgym.pass.dao.PassDAOOracle;

import com.passgym.gymequip.dao.GymEquipDAOOracle;
import com.passgym.gymequip.vo.GymEquip;
import com.passgym.pass.dao.PassDAOOracle;
import com.passgym.pass.vo.Pass;

public class GymService {
	private GymDAOOracle gymDAO = GymDAOOracle.getInstance();
	private PassDAOOracle passDAO = PassDAOOracle.getInstance();
	private GymEquipDAOOracle gymEquipDAO = GymEquipDAOOracle.getInstance();
	private static GymService service = new GymService();
	private GymService() {}
	public static GymService getInstance() {
		return service;
	}
public void gymRegist(Gym gym, List<Pass> passes, List<GymEquip> gymEquips) throws AddException {
	gymDAO.add(gym);
	passDAO.add(passes);
	gymEquipDAO.add(gymEquips);
}
  
public List<Pass> findByOwnerNo(int ownerNo) throws FindException {
		return gymDAO.findByOwnerNo(ownerNo);
	}

public Gym findGym(int ownerNo) throws FindException{
	Gym gym = gymDAO.findGymByOwnerNo(ownerNo);
	return gym;
	}
	
	public List<Gym> findByDistance(double latitude, double longitude) throws FindException{
		List<Gym> gymList = gymDAO.findByDistance(latitude, longitude);
		
		return gymList;
	}
	
	public List<Gym> findByAvgStar(double latitude, double longitude) throws FindException{
		List<Gym> avgStarList = gymDAO.findByAvgStar(latitude, longitude);
		
		return avgStarList;
	}
	
	public List<Gym> findZzim(int userNo, double latitude, double longitude) throws FindException{
		List<Gym> zzimList = gymDAO.findZzim(userNo, latitude, longitude);
		
		return zzimList;
	}
	
	public Gym gymDetail(int ownerNo, double latitude, double longitude) throws FindException{
		Gym gymDetail = gymDAO.gymDetail(ownerNo, latitude, longitude);
		
		return gymDetail;
	}
	
}
