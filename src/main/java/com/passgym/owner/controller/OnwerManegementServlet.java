package com.passgym.owner.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.passgym.exception.FindException;
import com.passgym.gympass.vo.GymPass;
import com.passgym.owner.service.OwnerService;
import com.passgym.owner.vo.Owner;
import com.passgym.pass.vo.Pass;
import com.passgym.payment.vo.Payment;
import com.passgym.user.vo.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/onwermanegement")
public class OnwerManegementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	String msg = "";
	
	Owner o = (Owner)session.getAttribute("loginInfo");
	if(o == null) {
		//1. 로그인을 되어있지 않을 경우
		msg = "로그인하세요";
		request.setAttribute("msg", msg);
	}else {
		//2. 로그인 된 경우 
		OwnerService service = new OwnerService();
		int ownerNo = o.getOwnerNo();
		try {
			List<Pass> passes = service.findByOwnerNo(ownerNo);
			request.setAttribute("passes", passes);
//			System.out.println(ownerNo + "이용권 종류 : "+ passes.size());
//			for(Pass p: passes) {
//				System.out.println("<이용권 정보>");
//				System.out.println(p);
//				System.out.println("-----헬스장 이용권 구매한 회원 내역 --------");
//				System.out.println("id : name : paymentNo :paymentPrice");
//				for(GymPass gp: p.getGympasses()) {
//					User u = gp.getUser();
//					Payment pay = gp.getPayment();
//					System.out.println(u.getId() + ":" + u.getName() + ":" + pay.getPaymentNo() + ":" + pay.getPaymentPrice());
//				}
//				System.out.println("-----------------------");
//			}
		}catch(FindException e) {
			request.setAttribute("msg", e.getMessage());
		}
	 
		
	}
	String path="GymOwner.jsp";
	RequestDispatcher rd = request.getRequestDispatcher(path);
	rd.forward(request, response);
} 
}
 
