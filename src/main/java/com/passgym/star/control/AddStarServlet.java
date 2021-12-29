package com.passgym.star.control;

import java.io.IOException;

import com.passgym.exception.AddException;
import com.passgym.star.service.StarService;
import com.passgym.star.vo.Star;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddStarServlet
 */
@WebServlet("/addstar")
public class AddStarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request 속성값 받기
		String paymentNo = (String)request.getParameter("paymentNo");
		int starPoint = Integer.parseInt(request.getParameter("starPoint"));
		Star star = new Star(paymentNo, starPoint);
		
		StarService service = StarService.getInstance();
		String Path = "jsonresult.jsp";
		String resultMsg = "";
		
		//비지니스 로직 호출
		try {
			service.addStar(star);
			resultMsg = "별점주기 성공";
			request.setAttribute("status", 1);
		} catch (AddException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			resultMsg = "별점주기 실패";
			request.setAttribute("status", 0);
		}
		
		request.setAttribute("msg", resultMsg);
		
		//viewer로 이동
		RequestDispatcher rd = request.getRequestDispatcher(Path);
		rd.forward(request, response);
	}

}
