package com.passgym.owner.controller;

import java.io.IOException;
import java.util.List;

import com.passgym.exception.FindException;
import com.passgym.owner.service.OwnerService;
import com.passgym.owner.vo.Owner;
import com.passgym.pass.vo.Pass;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ownermanegement")
public class OwnerManegementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String msg = "";

		Owner o = (Owner) session.getAttribute("loginInfo");
		//----샘플 로그인 정보-------------------------------
		o = new Owner(); 
		o.setOwnerNo(1);
		//----------------------------------------------
 		if (o == null) {
 			// 1. 로그인을 되어있지 않을 경우
 			msg = "로그인하세요";
 			request.setAttribute("msg", msg);
			} else {
			// 2. 로그인 된 경우
			
			OwnerService service = OwnerService.getInstance();
			int ownerNo = o.getOwnerNo();
			try {
			
				List<Pass> passes = service.findByOwnerNo(ownerNo);
				System.out.println(passes.toString());
				
				request.setAttribute("passes", passes);
				
			} catch (FindException e) {
				request.setAttribute("msg", e.getMessage());
			}

		}
		String path = "/usermanagement.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
