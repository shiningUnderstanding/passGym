package com.passgym.gym.control;

import java.io.IOException;
import java.util.List;

import com.passgym.exception.FindException;
import com.passgym.gym.service.GymService;
import com.passgym.gym.vo.Gym;
import com.passgym.pass.service.PassService;
import com.passgym.pass.vo.Pass;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/gymdetail")
public class GymDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GymService gymService = GymService.getInstance();
	private PassService passService = PassService.getInstance();
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "";
		int ownerNo = Integer.parseInt(request.getParameter("onwerNo"));
		String lat = (String)request.getAttribute("latitude");
		String lon = (String)request.getAttribute("latitude");
		double latitude = 0.0;
		double longitude = 0.0;
		if(lat == null && lon == null) {
			latitude = 0.0;
			longitude = 0.0;
		} else {
			latitude = Double.parseDouble(lat);
			longitude = Double.parseDouble(lon);
		}
		try {
			Gym gymDetail = gymService.gymDetail(ownerNo, latitude, longitude);//사업자번호로 해당 헬스장 정보 찾기
			List<Pass> passes = passService.findPassByOwnerNo(ownerNo);
			request.setAttribute("gymDetail", gymDetail);
			request.setAttribute("passes", passes);
			System.out.println(gymDetail);
			System.out.println(passes);
			path = "gymdetailresult.jsp";
		} catch (FindException e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}

}
