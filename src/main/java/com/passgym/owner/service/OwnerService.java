package com.passgym.owner.service;


import com.passgym.exception.AddException;
import com.passgym.gym.dao.GymDAOOracle.GymDAOOracle;
import com.passgym.gym.vo.Gym;
import com.passgym.owner.dao.OwnerDAOOracle;
import com.passgym.owner.vo.Owner;

public class OwnerService {
	private OwnerDAOOracle ownerDAO;
	
	public void ownerSignup(Owner owner) throws AddException{
		ownerDAO = new OwnerDAOOracle();
		ownerDAO.add(owner);
	}

	public void findByOwnerNo(int ownerNo) throws AddException{
	 
	}

}
