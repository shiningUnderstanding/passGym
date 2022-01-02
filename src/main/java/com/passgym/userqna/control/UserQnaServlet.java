package com.passgym.userqna.control;

import java.io.IOException;

import com.passgym.exception.AddException;
import com.passgym.user.vo.User;
import com.passgym.userqna.service.UserQnaService;
import com.passgym.userqna.vo.UserQna;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class UserQnaServlet
 */
@WebServlet("/userqna")
public class UserQnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "jsonresult.jsp";
		String resultMsg = "";
		
		//session객체 받아오기 userNo, userId
		HttpSession session = request.getSession();
		User sessionUser = (User)session.getAttribute("userLoginInfo");
		
		//파라미터 값 받아오기
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		UserQna userQna = new UserQna();
		userQna.setTitle(title);
		userQna.setContent(content);
		userQna.setUserNo(sessionUser.getUserNo());
		
		//서비스 받아오기
		UserQnaService service = UserQnaService.getInstance();
		
		//비지니스로직 구현
		try {
			service.addUserQna(userQna);
			
			//결과값설정
			request.setAttribute("status", 1);
			resultMsg = "문의성공";
		} catch (AddException e) {
			e.printStackTrace();
			request.setAttribute("status", 0);
			resultMsg = "문의실패";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
