package com.passgym.pass.control;

import java.io.IOException;

import com.passgym.exception.FindException;
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
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("userLoginInfo");
		int ownerNo = Integer.parseInt(request.getParameter("onwerNo"));
		String paymentNo = request.getParameter("paymentNo");
		int paymentPrice = Integer.parseInt(request.getParameter("paymentPrice"));
		int paymentType = Integer.parseInt(request.getParameter("paymentType"));
		String bankName = request.getParameter("bankName");
		String path = "";
		String msg = "";
		GymPass gp = new GymPass();
		Pass pass = new Pass();
		if(u == null) {
			msg = "로그인이 필요한 서비스입니다.";
			path = "login.jsp";
		} else {
			
		}
		
	}

}
