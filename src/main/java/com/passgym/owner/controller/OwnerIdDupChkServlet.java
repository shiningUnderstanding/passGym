package com.passgym.owner.controller;

import java.io.IOException;

import com.passgym.exception.FindException;
import com.passgym.owner.service.OwnerService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/owneriddupchk")
public class OwnerIdDupChkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idValue = request.getParameter("id");
		
		String path = "jsonresult.jsp";
		String resultMsg = "";
		
		OwnerService service = OwnerService.getInstance();
		
		try {
			service.ownerIdDupChk(idValue);
			resultMsg = "이미 사용중인 아이디입니다";
			request.setAttribute("status", 0);
		} catch (FindException e) {
			resultMsg = "사용가능한 아이디입니다.";
			request.setAttribute("status", 1);
		}
		
		request.setAttribute("msg", resultMsg);
		
		//5. VIEWER로 이동
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}

}
