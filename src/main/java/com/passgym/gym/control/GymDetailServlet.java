package com.passgym.gym.control;

import java.io.IOException;

import com.passgym.exception.FindException;
import com.passgym.gym.service.GymService;
import com.passgym.gym.vo.Gym;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/gymdetail")
public class GymDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GymService service = new GymService();
		String path = "";
		int ownerNo = Integer.parseInt(request.getParameter("ownerNo"));
		
		try {
			Gym g = service.findGym(ownerNo);
			request.setAttribute("g", g);
			path = "gymdetailresult.jsp";
		} catch (FindException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}

}
