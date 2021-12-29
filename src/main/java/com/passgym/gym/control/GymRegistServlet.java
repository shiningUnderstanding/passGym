package com.passgym.gym.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.gym.service.GymService;
import com.passgym.gym.vo.Gym;
import com.passgym.owner.vo.Owner;
import com.passgym.pass.vo.Pass;

import jakarta.servlet.RequestDispatcher;
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
		Owner sessionOwner = (Owner)session.getAttribute("signupInfo");
		
		int ownerNo = sessionOwner.getOwnerNo();
		GymService service = new GymService();
		Gym gym;
		
		Pass pass = new Pass();
		List<Pass> passes = new ArrayList<Pass>();
		String iSend = request.getParameter("i");
		
		for(int i = 0; i < Integer.parseInt(iSend) + 1; i++) {
			passes.add(pass);
			Pass passComponent = passes.get(i);
			String iString = Integer.toString(i);
			passComponent.setOwnerNo(ownerNo);
			passComponent.setPassNo(Integer.parseInt(request.getParameter("passno"+i)));
			passComponent.setPassName(request.getParameter("passname"+i));
			passComponent.setPassStatus(1);
			passComponent.setPassPrice(Integer.parseInt(request.getParameter("passprice"+i)));
			passComponent.setPassMonth(Integer.parseInt(request.getParameter("passmonth"+i)));
			passComponent.setPauseCount(Integer.parseInt(request.getParameter("pausecount"+i)));
			passComponent.setPauseDate(Integer.parseInt(request.getParameter("pausedate"+i)));
		}
		
		String path = "jsonresult.jsp";
		String resultMsg = "";
		
		try {
			gym = service.findGym(ownerNo);
			gym.setIntroduce(request.getParameter("introduce"));
			gym.setNotice(request.getParameter("notice"));
			gym.setOperatingTime(request.getParameter("operatingtime"));
			gym.setOperatingProgram(request.getParameter("operatingprogram"));
			gym.setExtraService(request.getParameter("extraservice"));
			gym.setEtc(request.getParameter("etc"));
			
			service.gymRegist(gym, passes);
			
			request.setAttribute("status", 1);
			resultMsg = "gym등록성공";
			
		} catch (FindException e1) {
			e1.printStackTrace();
			request.setAttribute("status", 0);
			resultMsg = "gym등록실패";
		} catch(AddException e) {
			e.printStackTrace();
			request.setAttribute("status", 0);
			resultMsg = "gym등록실패";
		}
		
		request.setAttribute("msg", resultMsg);

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}

}
