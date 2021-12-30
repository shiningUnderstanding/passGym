package com.passgym.owner.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.passgym.exception.AddException;
import com.passgym.gym.vo.Gym;
import com.passgym.owner.service.OwnerService;
import com.passgym.owner.vo.Owner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ownersignup")
public class OwnerSignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phoneNo = request.getParameter("phoneno");
		int ownerNo = Integer.parseInt(request.getParameter("registno"));
		String pwd = request.getParameter("pwd");
		String zipCode = request.getParameter("zipcode");
		String addr = request.getParameter("addr");
		String addrDetail = request.getParameter("addrdetail");
		double lat = 0.0;
		double lon = 0.0;
		
		Owner owner = new Owner(ownerNo, id, pwd, 1);
		Gym gym = new Gym(ownerNo, name, phoneNo, zipCode, 
							addr, addrDetail, null, null, null, null, null, null, 0, 0, 0, lat, lon, 0);
		
		OwnerService service = new OwnerService();
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		session.removeAttribute("signupInfo");
		session.removeAttribute("gymInfo");
		
		try {
			service.ownerSignup(owner, gym);
			session.setAttribute("signupInfo", owner);
			session.setAttribute("gymInfo", gym);
			out.print("1");
			
			Owner ownerSession = (Owner)session.getAttribute("signupInfo");
			System.out.println("세션저장객체 : " + ownerSession.toString());
			
		} catch (AddException e) {
			out.print("0");
			e.printStackTrace();
		}
	}

}
