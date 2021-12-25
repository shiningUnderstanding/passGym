package com.passgym.gym.service;

import com.passgym.exception.AddException;
import com.passgym.gym.dao.GymDAOOracle.GymDAOOracle;
import com.passgym.gym.vo.Gym;

public class GymService {
	GymDAOOracle dao;
	
	public void gymRegist(Gym gym) throws AddException {
		dao = new GymDAOOracle();
		dao.add(gym);
	}
}
