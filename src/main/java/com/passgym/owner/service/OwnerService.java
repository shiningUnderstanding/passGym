package com.passgym.owner.service;

import java.util.List;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.gym.dao.GymDAOOracle.GymDAOOracle;
import com.passgym.pass.vo.Pass;

public class OwnerService {
	public  List<Pass> findByOwnerNo(int ownerNo) throws FindException {
		GymDAOOracle dao = new GymDAOOracle();
		return dao.findByOwnerNo(ownerNo);
	}
}
