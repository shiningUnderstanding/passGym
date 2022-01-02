package com.passgym.pass.service;

import java.util.List;

import com.passgym.exception.FindException;
import com.passgym.pass.dao.PassDAOOracle;
import com.passgym.pass.vo.Pass;

public class PassService {
	private static PassService service = new PassService();
	private PassService() {}
	public static PassService getInstance() {
		return service;
	}
	private PassDAOOracle dao = PassDAOOracle.getInstance();
	
	public Pass findPass(int ownerNo, int passNo) throws FindException{
		return dao.findPass(ownerNo, passNo);
	}
	
	public List<Pass> findPassByOwnerNo(int ownerNo) throws FindException{
		return dao.findPassByOwnerNo(ownerNo);
	}

}
