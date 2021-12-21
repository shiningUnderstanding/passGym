package com.passgym.zzim.vo;

public class Zzim {
	private int userNo;
	private int ownerNo;
	
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
