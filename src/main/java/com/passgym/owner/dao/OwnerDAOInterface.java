package com.passgym.owner.dao;

import com.passgym.exception.AddException;
import com.passgym.owner.vo.Owner;

public interface OwnerDAOInterface {
	
	/**
	 * 해당 ownerNo를 가진 Owner객체를 반환한다
	 * @param ownerNo 회원가입시에 부여된 판매자 번호.
	 * @return Owner객체
	 */
	public Owner findByOwnerId(String ownerNo);
	
	/**
	 * 저장소에 판매자를 추가한다
	 * @param owner 추가할 판매자
	 * @throws AddException
	 * 추가할 때 판매자 번호를 부여하고 이를 사용해서 로그인을 한다. id대신 사용됨
	 */
	public void add(Owner owner) throws AddException;
}
