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
	//
	private Payment payment;
	private Star star;
	//
	public GymPass() {}

}
