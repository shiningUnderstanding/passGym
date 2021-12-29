package com.passgym.payment.dao;

import com.passgym.exception.AddException;
import com.passgym.payment.vo.Payment;

public interface PaymentDAOInterface {

	public void addPayment(Payment p) throws AddException;
}
