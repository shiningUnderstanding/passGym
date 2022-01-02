package com.passgym.gym.dao;

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
	 * 거리, 별점순으로 헬스장 정보를 출력한다(위치정보 미동의 시 서울역기준)
	 * @param latitude 위도
	 * @param longitude 경도
	 * @return 거리, 별점순 헬스장
	 * @throws FindException
	 */
	public List<Gym> findByDistance(double latitude, double longitude) throws FindException;
	/**
	 * 찜한 헬스장을 거리, 별점순으로 출력(찜이 없으면 출력값 없음, 위치정보 미동의 시 서울역기준)
	 * @param userNo 사용자번호
	 * @param latitude 위도
	 * @param longitude 경도
	 * @return 찜한 헬스장
	 * @throws FindException 찜한 헬스장이 없는 경우 "찜한 헬스장이 없습니다"메시지를 갖는 예외가 발생한다
	 */
	public List<Gym> findZzim(int userNo, double latitude, double longitude) throws FindException;
	/**
	 * 헬스장 상세 페이지에 넘겨줄 gym정보 모두 출력
	 * @param ownerNo 사업자번호
	 * @param latitude 위도
	 * @param longitude 경도
	 * @return 사업자번호에 해당하는 헬스장 상세 정보
	 * @throws FindException
	 */
	public Gym gymDetail(int ownerNo, double latitude, double longitude) throws FindException;
	
	/**
	 * 판매자회원가입화면에서 입력한 정보를 저장한다.
	 * @param gym
	 * @throws AddException
	 */
	public void signupAdd(Gym gym) throws AddException;
	 
}