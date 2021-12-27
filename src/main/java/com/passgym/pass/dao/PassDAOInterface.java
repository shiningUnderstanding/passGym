package com.passgym.pass.dao;

import com.passgym.exception.AddException;
import com.passgym.pass.vo.Pass;

public interface PassDAOInterface {
	public void add(Pass pass) throws AddException; 
}
