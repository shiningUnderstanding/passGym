package com.passgym.pass.control;

import java.io.IOException;

import com.passgym.gympass.vo.GymPass;
import com.passgym.pass.vo.Pass;
import com.passgym.user.vo.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/paymentpass")
public class PaymentPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paymentNo = request.getParameter("paymentNo");
		int paymentPrice = Integer.parseInt(request.getParameter("paymentPrice"));
		int paymentType = Integer.parseInt(request.getParameter("paymentType"));
		String bankName = request.getParameter("bankName");
		
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("userLoginInfo");
		int status = 0;
		String msg = "";
		GymPass gp = new GymPass();
		Pass pass = new Pass();
		gp.setUser(u);
		
	}

}
