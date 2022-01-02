package com.passgym.ownerqna.dao;

import com.passgym.exception.AddException;
import com.passgym.ownerqna.vo.OwnerQna;

public interface OwnerQnaDAOInterface {
	 public void addOwnerQna(OwnerQna ownerQna) throws AddException;
}
