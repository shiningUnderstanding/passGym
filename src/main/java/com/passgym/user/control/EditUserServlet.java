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
 * Servlet implementation class EditUserServlet
 */
@WebServlet("/loadedituser")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//path설정
		String path = "edituser.jsp";
		
		//세션정보 획득
		HttpSession session = request.getSession();
		User sessionUser = (User)session.getAttribute("userLoginInfo");
		
		//서비스 객체 획득
		UserService service = UserService.getInstance();
		//비지니스로직 호출
		try {
			User requestUser = service.findUserByNo(sessionUser.getUserNo());
			System.out.println("유저수정페이지가 호출되었습니다.");
			request.setAttribute("user", requestUser);
			System.out.println("컨트롤러에서 User 객체 잘 받아왔습니다.");
		} catch (FindException e) {
			e.printStackTrace();
		}
		
		//viwer로 이동
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
