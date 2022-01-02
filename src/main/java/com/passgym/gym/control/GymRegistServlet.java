package com.passgym.gym.control;

import java.io.IOException;
import java.io.PrintWriter;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.gym.service.GymService;
import com.passgym.gym.vo.Gym;
import com.passgym.owner.vo.Owner;
import com.passgym.pass.vo.Pass;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/gymregist")
public class GymRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GymService service = GymService.getInstance();
      

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Owner sessionOwner = (Owner)session.getAttribute("signupInfo");
		
		int ownerNo = sessionOwner.getOwnerNo();
		Gym gym;
		Pass pass;
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			gym = service.findGym(ownerNo);
			gym.setIntroduce(request.getParameter("introduce"));
			gym.setNotice(request.getParameter("notice"));
			gym.setOperatingTime(request.getParameter("operatingtime"));
			gym.setOperatingProgram(request.getParameter("operatingprogram"));
			gym.setExtraService(request.getParameter("extraservice"));
			gym.setEtc(request.getParameter("etc"));
			
			service.gymRegist(gym);
			out.print("1");

		} catch (FindException e1) {
			e1.printStackTrace();
			out.print("0");
		} catch(AddException e) {
			out.print("10");
			e.printStackTrace();
		}
		
	}

}
