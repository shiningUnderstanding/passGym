package com.passgym.pass.dao;

import java.util.List;

import com.passgym.exception.AddException;
import com.passgym.pass.vo.Pass;

public interface PassDAOInterface {
	
	public void add(Pass pass) throws AddException;

	public void add(List<Pass> passes) throws AddException; 
}
