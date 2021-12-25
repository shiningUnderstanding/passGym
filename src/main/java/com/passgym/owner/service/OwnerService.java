package com.passgym.owner.service;

import java.util.List;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.gym.dao.GymDAOOracle.GymDAOOracle;

import com.passgym.gym.vo.Gym;
import com.passgym.owner.dao.OwnerDAOOracle;
import com.passgym.owner.vo.Owner;
import com.passgym.pass.vo.Pass;

public class OwnerService {
  private OwnerDAOOracle ownerDAO;
  
  public void ownerSignup(Owner owner) throws AddException{
		ownerDAO = new OwnerDAOOracle();
		ownerDAO.add(owner);		
	}
  
	public  List<Pass> findByOwnerNo(int ownerNo) throws FindException {
		GymDAOOracle dao = new GymDAOOracle();
		return dao.findByOwnerNo(ownerNo);
	}
}