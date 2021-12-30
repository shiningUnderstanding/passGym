package com.passgym.user.service;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.exception.ModifyException;
import com.passgym.exception.RemoveException;
import com.passgym.user.dao.UserDAOOracle;
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

	public void useriddupchk(String id) throws FindException{
		dao.findByUserId(id);
	}
	
	public void phonenumdupchk(String phoneNo) throws FindException{
		dao.findByPhoneNo(phoneNo);
	}
	
	public void usersignup(User u) throws AddException{
		dao.addUser(u);
	}
	
	public User mypageFindByNo(int userNo) throws FindException{
		return dao.mypageFindByNo(userNo);
	}
	
	public User findUserByNo(int userNo) throws FindException{
		return dao.findByUserNo(userNo);
	}
	
	public void editUser(User user) throws ModifyException{
		dao.modifyUser(user);
	}
	
	public void withdrawal(User user) throws ModifyException{
		dao.removeUser(user);
	}
}
