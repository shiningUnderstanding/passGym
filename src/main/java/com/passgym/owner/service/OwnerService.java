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
  private GymDAOOracle gymDAO;
  
  public void ownerSignup(Owner owner, Gym gym) throws AddException{
		ownerDAO = new OwnerDAOOracle();
		gymDAO = new GymDAOOracle();
		ownerDAO.add(owner);		
		gymDAO.signupAdd(gym);
	}
  
  
	public  List<Pass> findByOwnerNo(int ownerNo) throws FindException {
		GymDAOOracle dao = new GymDAOOracle();
		return dao.findByOwnerNo(ownerNo);
	}
	

	public Owner login(String id, String pwd) throws FindException {
		ownerDAO = new OwnerDAOOracle();
		try {
			Owner owner = ownerDAO.findByOwnerId(id);
			if(owner.getPwd().equals(pwd)) {
				return owner;
			}
			throw new FindException();
		} catch (FindException e) {
			throw new FindException("로그인 실패");
		}
		
	}


	public void ownerIdDupChk(String idValue) throws FindException {
		ownerDAO = new OwnerDAOOracle();
		ownerDAO.findByOwnerId(idValue);
	}
}