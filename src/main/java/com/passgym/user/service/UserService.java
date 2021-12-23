package com.passgym.user.service;

import com.passgym.user.vo.User;

public class UserService {
	private UserDAOOracle dao = UserDAOOracle.getInstance();
	private static UserService service = new UserService();
	private UserService() {}
	public static UserService getInstance() {
		return service;
	}
	
	public User login(String id, String pwd) throws FindException{
		try{
			User u = dao.findByUserId(id);
			if(u.getPwd().equals(pwd)) {
				return u;
			}
			throw new FindException();
		} catch(FindException e) {
			throw new FindException("로그인 실패");
		}
	}
	public void 
}