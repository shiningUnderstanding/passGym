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
	 * 회원가입 후 헬스장 정보를 등록한다.
	 * @param gym 등록할 헬스장 정보
	 * @throws AddException
	 */
	public void add(Gym gym) throws AddException;
	/**
	 * 거리순으로 헬스장 정보를 출력한다
	 * @param latitude 위도
	 * @param longitude 경도
	 * @return 거리순 헬스장
	 * @throws FindException
	 */
	public List<Gym> findByDistance(double latitude, double longitude) throws FindException;
	
	public double printAvgStar(int ownerNo) throws FindException; 

}