package com.passgym.userqna.service;

import com.passgym.exception.AddException;
import com.passgym.userqna.dao.UserQnaDAOOracle;
import com.passgym.userqna.vo.UserQna;

public class UserQnaService {
	UserQnaDAOOracle dao = UserQnaDAOOracle.getInstance();
	
	private static UserQnaService service = new UserQnaService();
	private UserQnaService() {}
	public static UserQnaService getInstance() {
		return service;
	}
	
	public void addUserQna(UserQna userQna) throws AddException {
		dao.addUserQna(userQna);
	}
}
