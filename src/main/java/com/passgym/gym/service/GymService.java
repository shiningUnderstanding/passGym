package com.passgym.gym.service;

import java.util.List;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.gym.dao.GymDAOOracle;
import com.passgym.gym.vo.Gym;
import com.passgym.pass.dao.PassDAOOracle;
import com.passgym.pass.vo.Pass;

public class GymService {
	GymDAOOracle gymDAO;
	PassDAOOracle passDAO;
	
	public void gymRegist(Gym gym, List<Pass> passes) throws AddException {
		gymDAO = new GymDAOOracle();
		passDAO = new PassDAOOracle();
		gymDAO.add(gym);
		passDAO.add(passes);
	}
	
	public Gym findGym(int ownerNo) throws FindException{
		gymDAO = new GymDAOOracle();
		Gym gym = gymDAO.findGymByOwnerNo(ownerNo);
		return gym;
	}
}
