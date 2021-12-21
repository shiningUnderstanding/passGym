package com.passgym.owner.vo;

import com.passgym.gym.vo.Gym;

public class Owner {
	private int owner_no;
	private String id;
	private String pwd;
	
	//
	//private Gym gym;
	//
	
	public Owner() {}

	public Owner(int owner_no, String id, String pwd) {
		super();
		this.owner_no = owner_no;
		this.id = id;
		this.pwd = pwd;
	}

	public int getOwner_no() {
		return owner_no;
	}

	public void setOwner_no(int owner_no) {
		this.owner_no = owner_no;
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
		return "Owner [owner_no=" + owner_no + ", id=" + id + ", pwd=" + pwd + "]";
	}
}
