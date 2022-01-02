package com.passgym.pass.dao;

import java.util.List;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.pass.vo.Pass;

public interface PassDAOInterface {
	
	public Pass findPass(int ownerNo, int passNo) throws FindException;
  
	public void add(List<Pass> passes) throws AddException; 
	
	public List<Pass> findPassByOwnerNo(int ownerNo) throws FindException;
	
}
