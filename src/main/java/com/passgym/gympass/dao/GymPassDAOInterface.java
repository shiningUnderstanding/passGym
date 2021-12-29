package com.passgym.gympass.dao;

import com.passgym.exception.AddException;
import com.passgym.gympass.vo.GymPass;

public interface GymPassDAOInterface {

	public void addGymPass(GymPass gp) throws AddException;
}
