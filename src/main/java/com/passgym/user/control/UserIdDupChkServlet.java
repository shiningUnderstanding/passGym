package com.passgym.user.control;

import java.io.IOException;

import com.passgym.exception.FindException;
import com.passgym.user.service.UserService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserIdDupChkServlet
 */
@WebServlet("/useriddupchk")
public class UserIdDupChkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service = UserService.getInstance();
	
    /**
     * Default constructor. 
     */
    public UserIdDupChkServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 요청전달데이터 얻기
		String idValue = request.getParameter("id");
		
		//2. 비지니스로직 호출
		String path = "jsonresult.jsp";
		String resultMsg = "";
		
		try {
			service.useriddupchk(idValue);
			//3. 응답결과 계산
			resultMsg = "이미 사용중인 이메일입니다";
			request.setAttribute("status", 0);
		} catch (FindException e) {
			//3. 응답결과 계산
			resultMsg = "사용가능한 이메일입니다";
			request.setAttribute("status", 1);
		}
		
		//4. 응답결과를 요청속성으로 설정
		request.setAttribute("msg", resultMsg);
		
		//5. VIEWER로 이동
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
