package com.passgym.owner.service;

import java.util.List;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.gym.dao.GymDAOOracle;
import com.passgym.gym.vo.Gym;
import com.passgym.owner.dao.OwnerDAOOracle;
import com.passgym.owner.vo.Owner;
import com.passgym.pass.vo.Pass;

public class OwnerService {
  private OwnerDAOOracle ownerDAO;
  private GymDAOOracle gymDAO = GymDAOOracle.getInstance();
  
  public void ownerSignup(Owner owner, Gym gym) throws AddException{
		ownerDAO = new OwnerDAOOracle();
		ownerDAO.add(owner);		
		gymDAO.signupAdd(gym);
	}
  
	public  List<Pass> findByOwnerNo(int ownerNo) throws FindException {
		return gymDAO.findByOwnerNo(ownerNo);
	}
}