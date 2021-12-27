package com.passgym.owner.controller;

import java.io.IOException;

import com.passgym.owner.service.OwnerService;
import com.passgym.owner.vo.Owner;

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
		session.removeAttribute("loginInfo");
		
		
	}

}
