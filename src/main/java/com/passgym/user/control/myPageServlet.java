package com.passgym.user.control;

import java.io.IOException;

import com.passgym.exception.FindException;
import com.passgym.user.service.UserService;
import com.passgym.user.vo.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class myPageServlet
 */
@WebServlet("/mypage")
public class myPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션의 유저정보 받아오기
		HttpSession session = request.getSession();
		User sessionUser = (User)session.getAttribute("userLoginInfo");
		String path = "";
		String resultMsg = "";
		//서비스 받아오기
		UserService service = UserService.getInstance();
		
		try {
			//서비스 호출
			User requestUser = service.mypageFindByNo();
			System.out.println("마이페이지가 호출되었습니다.");
			request.setAttribute("user", requestUser);
			path="mypage.jsp";
			
			//응답결과 만들기
		} catch (FindException e) {
			System.out.println(e.getMessage());
			//응답결과 만들기
			path="index.jsp";
		}
		
		//viewer로 이동
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
