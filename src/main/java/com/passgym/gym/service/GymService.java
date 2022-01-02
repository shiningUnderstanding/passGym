package com.passgym.gym.service;

import java.util.ArrayList;
import java.util.List;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.gym.dao.GymDAOOracle;
import com.passgym.gym.vo.Gym;
import com.passgym.pass.vo.Pass;

public class GymService {
	private GymDAOOracle dao = GymDAOOracle.getInstance();
	
	public List<Pass> findByOwnerNo(int ownerNo) throws FindException {
		return dao.findByOwnerNo(ownerNo);
	}
	
	
	public void gymRegist(Gym gym) throws AddException {
		dao.add(gym);
	}
	
	public Gym findGym(int ownerNo) throws FindException{
		Gym gym = dao.findGymByOwnerNo(ownerNo);
		return gym;
	}
	
	public List<Gym> findByDistance(double latitude, double longitude) throws FindException{
		List<Gym> gymList = dao.findByDistance(latitude, longitude);
		
		return gymList;
	}
	
	public List<Gym> findZzim(int userNo, double latitude, double longitude) throws FindException{
		List<Gym> zzimList = dao.findZzim(userNo, latitude, longitude);
		
		return zzimList;
	}
	
}
