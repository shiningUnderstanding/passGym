package com.passgym.ownerqna.control;

import java.io.IOException;

import com.passgym.owner.service.OwnerService;
import com.passgym.owner.vo.Owner;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ownerqnalist")
public class OwnerQnaListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션 유저정보 받기  
		HttpSession session = request.getSession();
		Owner o = (Owner)session.getAttribute("loginInfo");
		String path = "";
		 
		 	if(o == null) {  //로그인 유무 체크 
				//1. 로그인을 되어있지 않을 경우
			 path = "index.jsp";
			}else {
				//2. 로그인 된 경우 
				path = "ownerqnalist.jsp";
				
			}
		//viewer로 이동
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
		
		
	}
}
