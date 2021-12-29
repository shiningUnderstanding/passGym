package com.passgym.zzim.dao;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.exception.RemoveException;
import com.passgym.zzim.vo.Zzim;

public interface ZzimDAOInterface {
	/**
	 * 저장소에 고객이 찜한 gym객체를 추가한다.
	 * @param gym 추가할 gym객체
	 * @throws AddException 이미 찜한 gym의 경우에는 "이미 찜한 헬스장입니다." 메시지를 갖는 예외가 발생한다.<br>
	 */
	public void addZzim(Zzim zzim) throws AddException;
	
	/**
	 * 저장소에 고객이 찜한 gym객체를 제거한다.
	 * @param gym 제거할 gym객체
	 * @throws RemoveException 이미 제거된 gym의 경우 "찜목록에 없는 헬스장입니다." 메시지를 갖는 예외가 발생한다.(삭제했는데 목록에 그대로 남아있을 경우? 새로 로딩이 안됐다던지..)<br>
	 */
	public void removeZzim(Zzim zzim) throws RemoveException;
}
