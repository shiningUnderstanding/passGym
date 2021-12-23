package com.passgym.gym.dao.GymDAOOracle;

import java.util.List;

import com.passgym.exception.FindException;
import com.passgym.pass.vo.Pass;

public interface GymDAOInterface {
	/**
	 * 회원권목록별 헬스장 회원목록조회
	 * @param ownerNo 사업자번호
	 * @return
	 */
	List<Pass> findByOwnerNo(int ownerNo) throws FindException;
}
