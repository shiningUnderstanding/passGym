package com.passgym.gym.service;

import java.util.ArrayList;
import java.util.List;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.gym.dao.GymDAOOracle;
import com.passgym.gym.vo.Gym;
import com.passgym.pass.dao.PassDAOOracle;

public class GymService {
	private GymDAOOracle gymDAO = GymDAOOracle.getInstance();
	private PassDAOOracle passDAO = PassDAOOracle.getInstance();
	private static GymService service = new GymService();
	private GymService() {}
	public static GymService getInstance() {
		return service;
	}
	
	public void gymRegist(Gym gym) throws AddException {
		gymDAO.add(gym);
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
	
	public Gym gymDetail(int ownerNo, double latitude, double longitude) throws FindException{
		Gym gymDetail = gymDAO.gymDetail(ownerNo, latitude, longitude);
		
		return gymDetail;
	}
	
}
