package com.passgym.pass.dao;

import java.util.List;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.pass.vo.Pass;

public interface PassDAOInterface {
	public void add(Pass pass) throws AddException; 
	
	public Pass findPass(int ownerNo, int passNo) throws FindException;
	
	public List<Pass> findPassByOwnerNo(int ownerNo) throws FindException;
	
	
}
