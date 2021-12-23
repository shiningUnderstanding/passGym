package com.passgym.owner.vo;

import com.passgym.gym.vo.Gym;

public class Owner {
	private int ownerNo;
	private String id;
	private String pwd;
	
	public Owner() {}

	public Owner(int ownerNo, String id, String pwd) {
		super();
		this.ownerNo = ownerNo;
		this.id = id;
		this.pwd = pwd;
	}

	public int getOwner_no() {
		return ownerNo;
	}

	public void setOwner_no(int owner_no) {
		this.ownerNo = owner_no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	

	@Override
	public String toString() {
		return "Owner [owner_no=" + ownerNo + ", id=" + id + ", pwd=" + pwd + "]";
	}
}
