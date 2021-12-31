package com.passgym.user.control;

import java.io.IOException;

import com.passgym.exception.ModifyException;
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
 * Servlet implementation class UserWithdrawalSelvlet
 */
@WebServlet("/userwithdrawal")
public class UserWithdrawalSelvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path ="jsonresult.jsp";
		String resultMsg = "";
		
		HttpSession session = request.getSession();
		User sessionUser = (User)session.getAttribute("userLoginInfo");
		
		UserService service = UserService.getInstance();
		try {
			service.withdrawal(sessionUser);
			request.setAttribute("status", 1);
			resultMsg = "회원탈퇴에 성공하였습니다.";
			session.removeAttribute("userLoginInfo");
		} catch (ModifyException e) {
			e.printStackTrace();
			request.setAttribute("status", 0);
			resultMsg = "회원탈퇴에 실패하였습니다.";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
