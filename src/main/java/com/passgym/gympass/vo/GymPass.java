package com.passgym.gympass.vo;

import java.util.Date;

import com.passgym.pass.vo.Pass;
import com.passgym.payment.vo.Payment;
import com.passgym.star.vo.Star;
import com.passgym.user.vo.User;

public class GymPass {
	private int paymentNo;
	//private int ownerNo;
	public Pass pass;
	public User user;
	//private int passNo;
	//private int userNo;

	private Date startDate;
	private Date endDate;
	private int pauseCount;
	private int pauseDate;
	private int status;
	
	private Payment payment;
	private Star star;
	
	public GymPass() {}

	public GymPass(int paymentNo, Pass pass, User user, Date startDate, Date endDate, int pauseCount, int pauseDate,
			int status, Payment payment, Star star) {
		super();
		this.paymentNo = paymentNo;
		this.pass = pass;
		this.user = user;
		this.startDate = startDate;
		this.endDate = endDate;
		this.pauseCount = pauseCount;
		this.pauseDate = pauseDate;
		this.status = status;
		this.payment = payment;
		this.star = star;
	}
	
	
}
