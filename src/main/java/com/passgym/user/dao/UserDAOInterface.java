package com.passgym.user.dao;

import java.util.List;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.exception.ModifyException;
import com.passgym.gympass.vo.GymPass;
import com.passgym.user.vo.User;

public interface UserDAOInterface {
	/**
	 * 저장소에 user를 추가한다
	 * @param user 추가할 사용자객체
	 * @throws AddException
	 * ID가 중복된 경우에는 "이미 존재하는 아이디입니다."상세메세지를 갖는 예외가 발생한다.<br>
	 */
	public void addUser(User user) throws AddException;
	
	/**
	 * 아이디에 해당 고객을 저장소에서 찾아 반환한다
	 * @param id
	 * @return 사용자객체
	 * @throws FindException 아이디에 해당 사용자가 저장소에 없으면 "아이디에 해당하는 사용자가 없습니다"메시지를 갖는 예외발생한다<br>
	 */
	public User findByUserId(String id) throws FindException;
	
	/**
	 * 사용자번호에 해당 고객을 저장소에서 찾아 반환한다
	 * @param userNo
	 * @return 사용자객체
	 * @throws FindException 사용자번호에 해당 사용자가 저장소에 없으면 "사용자번호에 해당하는 사용자가 없습니다"메시지를 갖는 예외발생한다<br>
	 */
	public User findByUserNo(String userNo) throws FindException;
	
	/**
	 * 단어를 포함한 이름을 갖는 사용자들을 반환한다
	 * @param word 단어
	 * @return 사용자들
	 * @throws FindException 단어를 포함한 이름을 갖는 사용자가 한명도 없으면 "단어를 포함한 이름의 사용자는 없습니다"메시지를 갖는 예외가 발생한다<br>
	 */
	public List<User> findByUserName(String word) throws FindException;
	
	/**
	 * 이름과 휴대폰 번호로 해당 사용자 id를 찾아서 반환한다
	 * @param name 사용자 이름
	 * @param phoneNo 사용자 휴대폰 번호
	 * @return 해당 사용자의 id
	 * @throws FindException 이름과 휴대폰번호가 일치하는 고객이 없을 경우 "해당 이름 또는 휴대폰 번호를 가진 사용자가 없습니다."메시지를 갖는 예외가 발생한다<br>
	 */
	public String findUserId(String name, String phoneNo) throws FindException;
	
	/**
	 * 사용자번호(session)에 해당하는 사용자가 결제한 헬스장 목록을 반환한다
	 * @param userNo 사용자번호
	 * @return 해당 사용자의 결제한 헬스장목록
	 * @throws FindException
	 */
	public List<GymPass> findGymByUserNo(String userNo) throws FindException;
	
	/**
	 * 사용자번호(session)에 해당하는 사용자의 비밀번호를 수정한다<br>
	 * 단, 정보가 기존 내용과 같을 경우에는 변경하지 않는다
	 * @param pwd 변경할 비밀번호
	 * @throws ModifyException 기존 내용과 같을 경우 "현재 비밀번호와 같습니다"메시지를 갖는 예외가 발생한다<br>
	 */
	public void modifyUserPwd(String pwd) throws ModifyException;
	
	/**
	 * 사용자번호(session)에 해당하는 사용자의 주소를 수정한다<br>
	 * 단, 정보가 기존 내용과 같을 경우에는 변경하지 않는다
	 * @param zipCode 우편번호
	 * @param addr 도로명주소, 지번을 포함한 주소
	 * @param addrDetail 상세주소
	 * @throws ModifyException 기존 내용과 같을 경우 "기존 주소와 동일합니다"메시지를 갖는 예외가 발생한다<br>
	 */
	public void modifyUserAddr(String zipCode, String addr, String addrDetail) throws ModifyException;
}
