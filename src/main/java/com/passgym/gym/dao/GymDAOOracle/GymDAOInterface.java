package com.passgym.gym.dao.GymDAOOracle;

import java.util.Date;
import java.util.List;

import com.passgym.exception.FindException;
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
	 * 등록일자별로 회원권을 검색한다.
	 * @param at  등록한 날짜
	 * @return	목록정보( 회원권이용정보 + 구매이용권정보(결제)) 들
	 * @throws FindException
	 */
	// 반환시 날짜를 기준으로 이용을 등록한 날짜 기준으로 반환
	public List<Pass> findByDate(Date at) throws FindException;

	 
}
