package com.passgym.gym.service;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.gym.dao.GymDAOOracle.GymDAOOracle;
import com.passgym.gym.vo.Gym;

public class GymService {
	GymDAOOracle dao;
	
	public void gymRegist(Gym gym) throws AddException {
		dao = new GymDAOOracle();
		dao.add(gym);
	}
	
	public Gym findGym(int ownerNo) throws FindException{
		dao = new GymDAOOracle();
		Gym gym = dao.findGymByOwnerNo(ownerNo);
		return gym;
	}
}
