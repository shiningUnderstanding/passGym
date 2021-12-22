package com.passgym.zzim.vo;

import java.util.List;

import com.passgym.gym.vo.Gym;
import com.passgym.user.vo.User;

public class Zzim {
	private int userNo;
	private int ownerNo;
	
	//private User user;
	//private Gym gym;
	
	//
	//private List<User> users;
	//private List<Gym> gyms;
	//
	
	public Zzim() {}

	public Zzim(int userNo, int ownerNo) {
		super();
		this.userNo = userNo;
		this.ownerNo = ownerNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getOwnerNo() {
		return ownerNo;
	}

	public void setOwnerNo(int ownerNo) {
		this.ownerNo = ownerNo;
	}
	

	@Override
	public String toString() {
		return "Zzim [userNo=" + userNo + ", ownerNo=" + ownerNo + "]";
	}
}
