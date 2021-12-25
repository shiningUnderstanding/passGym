package com.passgym.gym.control;

import java.io.IOException;

import com.passgym.exception.AddException;
import com.passgym.gym.service.GymService;
import com.passgym.gym.vo.Gym;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/gymregist")
public class GymRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Gym sessionGym = (Gym)session.getAttribute("signupInfo");
		
		int ownerNo = sessionGym.getOwnerNo();
		String name = sessionGym.getName();
		String phoneNo = sessionGym.getPhoneNo();
		String zipCode = sessionGym.getZipcode();
		String addr = sessionGym.getAddr();
		String addrDetail = sessionGym.getAddrDetail();
		String introduce = request.getParameter("introduce");
		String notice = request.getParameter("notice");
		String operatingTime = request.getParameter("operatingtime");
		String operatingProgram = request.getParameter("operatingprogram");
		String extraService = request.getParameter("extraservice");
		String etc = request.getParameter("etc");
		double lat = sessionGym.getLat();
		double lon = sessionGym.getLon();
		
		Gym gym = new Gym(ownerNo, name, phoneNo, zipCode, 
							addr, addrDetail, introduce, notice, 
							operatingTime, operatingProgram, extraService, etc, 
							0, 0, lat, lon);
		
		GymService service = new GymService();
		
		try {
			service.gymRegist(gym);
		} catch (AddException e) {
			e.printStackTrace();
		}
		
	}

}
