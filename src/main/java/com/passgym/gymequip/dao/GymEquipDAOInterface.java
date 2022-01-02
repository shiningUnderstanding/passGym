package com.passgym.gymequip.dao;

import java.util.List;

import com.passgym.exception.AddException;
import com.passgym.gymequip.vo.GymEquip;

public interface GymEquipDAOInterface {
	
	
	/**
	 * 헬스장이 보유한 장비를 추가한다
	 * @param gymEquips 헬스장이 보유한 장비, 장비의 개수
	 * @throws AddException 
	 */
	public void add(List<GymEquip> gymEquips) throws AddException;
}
