package com.passgym.owner.vo;

import java.util.List;

import com.passgym.ownerqna.vo.OwnerQna;

public class Owner {
	private int ownerNo;
	private String id;
	private String pwd;
	private int ownerStatus;
	
	private List<OwnerQna> ownerQnas;
	
	public List<OwnerQna> getOwnerQnas() {
		return ownerQnas;
	}



	public void setOwnerQnas(List<OwnerQna> ownerQnas) {
		this.ownerQnas = ownerQnas;
	}



	public Owner() {}
 
	
	
	public Owner(int ownerNo, String id, String pwd, int ownerStatus) {
		super();
		this.ownerNo = ownerNo;
		this.id = id;
		this.pwd = pwd;
		this.ownerStatus = ownerStatus;
	}

	public int getOwnerNo() {
		return ownerNo;
	}

	public void setOwnerNo(int owner_no) {
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
	
	public int getOwnerStatus() {
		return ownerStatus;
	}

	public void setOwnerStatus(int ownerStatus) {
		this.ownerStatus = ownerStatus;
	}



	@Override
	public String toString() {
		return "Owner [ownerNo=" + ownerNo + ", id=" + id + ", pwd=" + pwd + ", ownerStatus=" + ownerStatus
				+ ", ownerQnas=" + ownerQnas + "]";
	}

 
}
