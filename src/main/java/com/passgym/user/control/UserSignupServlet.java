package com.passgym.user.control;

import java.io.IOException;

import com.passgym.exception.AddException;
import com.passgym.user.service.UserService;
import com.passgym.user.vo.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserSignupServlet
 */
@WebServlet("/usersignup")
public class UserSignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService service = UserService.getInstance();
    /**
     * Default constructor. 
     */
    public UserSignupServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = new User();
		
//		int userNo;
		u.setId(request.getParameter("id"));
		u.setPwd(request.getParameter("pwd"));
		u.setName(request.getParameter("name"));
		u.setPhoneNo(request.getParameter("phone_no"));
		u.setZipcode(request.getParameter("zipcode"));
		u.setAddr(request.getParameter("addr1"));
		u.setAddrDetail(request.getParameter("addr2")); 
//		String sns = "";
		
//		User u = new User(userNo, id, pwd, name, phoneNo, zipcode, addr, addrDetail, sns);
		
		String path = "jsonresult.jsp";
		String msg = "";
		try {
			service.usersignup(u);
			request.setAttribute("status", 1);
			msg = "가입성공";
		} catch (AddException e) {
			e.printStackTrace();
			request.setAttribute("status", 0);
			msg = e.getMessage();
		}
		request.setAttribute("msg", msg);
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
