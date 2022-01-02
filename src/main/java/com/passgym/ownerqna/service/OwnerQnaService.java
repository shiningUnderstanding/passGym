package com.passgym.ownerqna.service;

import com.passgym.exception.AddException;
import com.passgym.ownerqna.dao.OwnerQnaDAOOracle;
import com.passgym.ownerqna.vo.OwnerQna;

public class OwnerQnaService {
	OwnerQnaDAOOracle dao = OwnerQnaDAOOracle.getInstance();
	
	private static OwnerQnaService service = new OwnerQnaService();
	private OwnerQnaService() {}
	public static OwnerQnaService getInstance() {
		return service;
	}
	public void addOnwerQna(OwnerQna ownerQna) throws AddException{
		dao.addOwnerQna(ownerQna);
	}
}
 