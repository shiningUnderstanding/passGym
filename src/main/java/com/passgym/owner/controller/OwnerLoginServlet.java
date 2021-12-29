package com.passgym.owner.controller;

import java.io.IOException;

import com.passgym.exception.FindException;
import com.passgym.owner.service.OwnerService;
import com.passgym.owner.vo.Owner;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/ownerlogin")
public class OwnerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idValue = request.getParameter("id");
		String pwdValue = request.getParameter("pwd");
		System.out.println("id=" + idValue + ", pwd=" + pwdValue);
		
		String resultMsg = "";
		OwnerService service = new OwnerService();
		
		HttpSession session = request.getSession();
		session.removeAttribute("ownerLoginInfo");
		
		String path="jsonresult.jsp";
		
		try {
			
			Owner owner = service.login(idValue, pwdValue);
			session.setAttribute("ownerLoginInfo", owner);
			System.out.println("로그인 성공");
			
			resultMsg = "로그인 성공";
			request.setAttribute("ownerStatus", 1);
			request.setAttribute("status", 2);
		} catch (FindException e) {
			System.out.println(e.getMessage());
			resultMsg = "로그인 실패";
			request.setAttribute("ownerStatus", 0);
			request.setAttribute("status", 2);
		}
		
		request.setAttribute("msg", resultMsg);
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
		
		
		
	}

}
