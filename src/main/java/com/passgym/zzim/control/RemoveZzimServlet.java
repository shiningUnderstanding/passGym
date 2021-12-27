package com.passgym.zzim.control;

import java.io.IOException;

import com.passgym.exception.RemoveException;
import com.passgym.gym.vo.Gym;
import com.passgym.user.vo.User;
import com.passgym.zzim.service.ZzimService;
import com.passgym.zzim.vo.Zzim;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class RemoveZzimServlet
 */
@WebServlet("/removezzim")
public class RemoveZzimServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//User sessionUser = (User)session.getAttribute("userLoginInfo");
		User sessionUser = new User();
		sessionUser.setUserNo(2);
		Gym gym = new Gym();
		int ownerNo = Integer.parseInt(request.getParameter("ownerNo"));
		gym.setOwnerNo(ownerNo);
		Zzim zzim = new Zzim();
		zzim.setUser(sessionUser);
		zzim.setGym(gym);
		ZzimService zzimService = ZzimService.getInstance();
		
		String path ="jsonresult.jsp";
		String resultMsg = "";
		
		try {
			zzimService.removeZzim(zzim);
			resultMsg = "찜 삭제 성공";
			request.setAttribute("status", 1);
		} catch (RemoveException e) {
			e.printStackTrace();
			resultMsg = "찜 삭제 실패";
			request.setAttribute("status", 0);
		}
		
		//4. 응답결과를 요청속성으로 설정하기
		request.setAttribute("msg", resultMsg);
		
		//5. Viewer로 이동
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
