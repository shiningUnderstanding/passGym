package com.passgym.gym.control;

import java.io.IOException;
import java.util.List;

import com.passgym.exception.FindException;
import com.passgym.gym.service.GymService;
import com.passgym.gym.vo.Gym;
import com.passgym.user.vo.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/gymlist")
public class GymListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GymService service = GymService.getInstance();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("userLoginInfo");
		String path = "";
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
		if(u == null) {//미로그인 상태
			try {
				List<Gym> gymList = service.findByDistance(latitude, longitude);
				request.setAttribute("gymList", gymList);
				path = "gymlistresult.jsp";
				for(Gym g: gymList) {
					System.out.println(g);
				}
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		}else {//로그인 상태
			try {
				int userNo = u.getUserNo();
				List<Gym> zzimList = service.findZzim(userNo, latitude, longitude);//userNo에 해당하는 찜 헬스장 반환
				request.setAttribute("zzimList", zzimList);
				List<Gym> gymList = service.findByDistance(latitude, longitude);
				request.setAttribute("gymList", gymList);
				path = "gymlistresult.jsp";
				for(Gym g: zzimList) {
					System.out.println(g);
				}
				for(Gym g: gymList) {
					System.out.println(g);
				}
			} catch (FindException e) {
				e.printStackTrace();
				request.setAttribute("msg", e.getMessage());
			}
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
