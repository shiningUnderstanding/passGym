package com.passgym.equip.dao;

import java.util.List;

import com.passgym.equip.vo.Equip;

public interface EquipDAOInterface {
	
	/**
	 * DB에 등록되어있는 기구를 전부 조회함
	 * @return 서비스의 DB에 등록해둔 장비목록
	 */
	public List<Equip> findAll();
	
	
}
