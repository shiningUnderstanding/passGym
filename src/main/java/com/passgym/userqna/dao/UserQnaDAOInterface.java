package com.passgym.userqna.dao;

import com.passgym.exception.AddException;
import com.passgym.userqna.vo.UserQna;

public interface UserQnaDAOInterface {
	public void addUserQna(UserQna userQna) throws AddException;
}
