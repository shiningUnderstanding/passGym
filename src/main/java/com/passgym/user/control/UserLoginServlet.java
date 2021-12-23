package com.passgym.user.control;

import java.io.IOException;

import com.passgym.user.vo.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/userlogin")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UserLoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 요청전달데이터 id, pwd 값 얻기
		String idValue = request.getParameter("id");
		String pwdValue = request.getParameter("pwd");
		System.out.println("UserLoginServlet의 doPost() id=" + idValue + ", pwd=" + pwdValue);
		
		String resultMsg = "";
		
		HttpSession session = request.getSession();
		session.removeAttribute("userLoginInfo"); //초기화
		
		String path = "jsonresult.jsp";
		
		//2. 비지니스로직 호출
		User u = service.login(idValue, pwdValue);
		System.out.println("로그인 성공");
		session.setAttribute("userLoginInfo", u);
	}

}
