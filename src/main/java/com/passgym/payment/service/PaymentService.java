package com.passgym.payment.service;

import com.passgym.exception.AddException;
import com.passgym.payment.dao.PaymentDAOOracle;
import com.passgym.payment.vo.Payment;

public class PaymentService {
	private PaymentDAOOracle dao = new PaymentDAOOracle();
	private static PaymentService service = new PaymentService();
	private PaymentService() {}
	public static PaymentService getInstance() {
		return service;
	}
	
	public void addPayment(Payment p) throws AddException{
		dao.addPayment(p);
	}
}
