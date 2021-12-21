package com.passgym.payment.vo;

import java.util.Date;

public class Payment {
	private int paymentNo;
	private int paymentPrice;
	private int paymentType;
	private String bankName;
	private Date paymentDate;
	
	public Payment() {}

	public Payment(int paymentNo, int paymentPrice, int paymentType, String bankName, Date paymentDate) {
		super();
		this.paymentNo = paymentNo;
		this.paymentPrice = paymentPrice;
		this.paymentType = paymentType;
		this.bankName = bankName;
		this.paymentDate = paymentDate;
	}

	public int getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(int paymentNo) {
		this.paymentNo = paymentNo;
	}

	public int getPaymentPrice() {
		return paymentPrice;
	}

	public void setPaymentPrice(int paymentPrice) {
		this.paymentPrice = paymentPrice;
	}

	public int getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public String toString() {
		return "Payment [paymentNo=" + paymentNo + ", paymentPrice=" + paymentPrice + ", paymentType=" + paymentType
				+ ", bankName=" + bankName + ", paymentDate=" + paymentDate + "]";
	}
}
