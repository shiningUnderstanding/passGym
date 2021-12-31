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
 * Servlet implementation class PhoneNumDupChkServlet
 */
@WebServlet("/phonenumdupchk")
public class PhoneNumDupChkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service = UserService.getInstance();
    /**
     * Default constructor. 
     */
    public PhoneNumDupChkServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 요청전달데이터 얻기
				String phoneNumValue = request.getParameter("phone_no");
				System.out.println(phoneNumValue);
				//2. 비지니스로직 호출
				String path = "jsonresult.jsp";
				String resultMsg = "";
				
				try {
					service.phonenumdupchk(phoneNumValue);
					//3. 응답결과 계산
					resultMsg = "해당 번호로 가입된 사용자가 있습니다";
					request.setAttribute("status", 0);
				} catch (FindException e) {
					//3. 응답결과 계산
					resultMsg = "인증번호가 전송되었습니다";
					request.setAttribute("status", 1);
				}
				
				//4. 응답결과를 요청속성으로 설정
				request.setAttribute("msg", resultMsg);
				
				//5. VIEWER로 이동
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
			}

}
