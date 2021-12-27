package com.passgym.gym.dao.GymDAOOracle;

import java.util.Date;
import java.util.List;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.gym.vo.Gym;
import com.passgym.pass.vo.Pass;

public interface GymDAOInterface {
	/**
	 * 회원권목록별 헬스장 회원목록조회
	 * @param  ownerNo 사업자번호
	 * @return 목록정보( 회원권이용정보 + 구매이용권정보(결제)) 들
	 * @throws FindException
	 */
	public List<Pass> findByOwnerNo(int ownerNo) throws FindException;
	
	/**
	 * 판매자 회원가입 후 상세내용을 등록할 Gym을 찾는다.
	 * @param ownerNo Gym를 찾기 위한 사업자번호
	 * @return ownerNo를 가진 Gym
	 * @throws FindException
	 */
	public Gym findGymByOwnerNo(int ownerNo) throws FindException;
	
	/**
	 * 회원가입 후 헬스장 정보를 등록한다.
	 * @param gym 등록할 헬스장 정보
	 * @throws AddException
	 */
	public void add(Gym gym) throws AddException;

	/**
	 * 판매자회원가입화면에서 입력한 정보를 저장한다.
	 * @param gym
	 * @throws AddException
	 */
	public void signupAdd(Gym gym) throws AddException;

}