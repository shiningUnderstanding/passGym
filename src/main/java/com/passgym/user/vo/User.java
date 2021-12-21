package com.passgym.user.vo;

public class User {
	private int userNo; //사용자고유번호 
	private String id; //사용자아이디
	private String name; //사용자이름
	private String pwd; //비밀번호
	private String phoneNo; //핸드폰번호
	private int zipcode; //우편번호
	private String addr; //지번주소
	private String addrDetail; //상세주소
	private String sns; //sns(회원가입정보)
	
	public User() {}
	
	public User(int userNo, String id, String name, String pwd, String phoneNo, int zipcode, String addr,
			String addrDetail, String sns) {
		super();
		this.userNo = userNo;
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.phoneNo = phoneNo;
		this.zipcode = zipcode;
		this.addr = addr;
		this.addrDetail = addrDetail;
		this.sns = sns;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddrDetail() {
		return addrDetail;
	}

	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}

	public String getSns() {
		return sns;
	}

	public void setSns(String sns) {
		this.sns = sns;
	}

	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", id=" + id + ", name=" + name + ", pwd=" + pwd + ", phoneNo=" + phoneNo
				+ ", zipcode=" + zipcode + ", addr=" + addr + ", addrDetail=" + addrDetail + ", sns=" + sns + "]";
	}
}