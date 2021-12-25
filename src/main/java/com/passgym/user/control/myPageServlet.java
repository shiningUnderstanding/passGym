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
		//User sessionUser = (User)session.getAttribute("userLoginInfo");
		User sessionUser = new User();
		sessionUser.setUserNo(2);
		String path = "";
		
		if(sessionUser == null) {//로그인유무 체크
			path = "mypage.jsp";
		}else {
			//서비스 받아오기
			UserService service = UserService.getInstance();
			
			try {
				//서비스 호출
				User requestUser = service.mypageFindByNo(sessionUser.getUserNo());
				System.out.println("마이페이지가 호출되었습니다.");
				request.setAttribute("user", requestUser);
				System.out.println("컨트롤러에서 mypage User 객체 잘 받아왔습니다.");
				path="mypage.jsp";
			} catch (FindException e) {
				System.out.println(e.getMessage());
				path="mypage.jsp";
			}
		}
		
		//viewer로 이동
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
