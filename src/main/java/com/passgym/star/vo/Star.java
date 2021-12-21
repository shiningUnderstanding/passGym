package com.passgym.star.vo;

public class Star {
	private int paymentNo;
	private int star;
	
	public Star() {}

	public Star(int paymentNo, int star) {
		super();
		this.paymentNo = paymentNo;
		this.star = star;
	}

	public int getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(int paymentNo) {
		this.paymentNo = paymentNo;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	@Override
	public String toString() {
		return "Star [paymentNo=" + paymentNo + ", star=" + star + "]";
	}
}
