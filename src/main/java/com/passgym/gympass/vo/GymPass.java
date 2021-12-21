package com.passgym.gympass.vo;

import java.util.Date;

public class GymPass {
	private int paymentNo;
	private int ownerNo;
	private int passNo;
	private int userNo;
	private Date startDate;
	private Date endDate;
	private int pauseCount;
	private int pauseDate;
	private int status;
	
	public GymPass() {}

	public GymPass(int paymentNo, int ownerNo, int passNo, int userNo, Date startDate, Date endDate, int pauseCount,
			int pauseDate, int status) {
		super();
		this.paymentNo = paymentNo;
		this.ownerNo = ownerNo;
		this.passNo = passNo;
		this.userNo = userNo;
		this.startDate = startDate;
		this.endDate = endDate;
		this.pauseCount = pauseCount;
		this.pauseDate = pauseDate;
		this.status = status;
	}

	public int getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(int paymentNo) {
		this.paymentNo = paymentNo;
	}

	public int getOwnerNo() {
		return ownerNo;
	}

	public void setOwnerNo(int ownerNo) {
		this.ownerNo = ownerNo;
	}

	public int getPassNo() {
		return passNo;
	}

	public void setPassNo(int passNo) {
		this.passNo = passNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPauseCount() {
		return pauseCount;
	}

	public void setPauseCount(int pauseCount) {
		this.pauseCount = pauseCount;
	}

	public int getPauseDate() {
		return pauseDate;
	}

	public void setPauseDate(int pauseDate) {
		this.pauseDate = pauseDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "GymPass [paymentNo=" + paymentNo + ", ownerNo=" + ownerNo + ", passNo=" + passNo + ", userNo=" + userNo
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", pauseCount=" + pauseCount + ", pauseDate="
				+ pauseDate + ", status=" + status + "]";
	}
}
