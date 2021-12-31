package com.passgym.star.dao;

import com.passgym.exception.AddException;
import com.passgym.star.vo.Star;

public interface StarDAOInterface {
	/**
	 * 저장소에 Star를 추가한다
	 * @param star 추가할 사용자객체
	 * @throws AddException
	 * 별점이 추가 되지 못한 경우 "별점이 추가되지 못했습니다."상세메세지를 갖는 예외가 발생한다.<br>
	 */
	public void addStar(Star star) throws AddException;
}
