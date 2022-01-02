package com.passgym.gym.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.passgym.exception.AddException;
import com.passgym.exception.FindException;
import com.passgym.gym.service.GymService;
import com.passgym.gym.vo.Gym;
import com.passgym.gymequip.vo.GymEquip;
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
	private GymService service = GymService.getInstance();
      

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Owner sessionOwner = (Owner)session.getAttribute("signupInfo");
		
		int ownerNo = sessionOwner.getOwnerNo();
		Gym gym;
		
		Pass pass;
		List<Pass> passes = new ArrayList<Pass>();
		String iSend = request.getParameter("i");
		
		GymEquip gymEquip;
		List<GymEquip> gymEquips = new ArrayList<GymEquip>();
		
		
		//전체 pass의 개수를 html 태그로 추가한 후 serialize해서 정보를 받아오는 방법
		for(int i = 0; i < Integer.parseInt(iSend)+1; i++) {
			pass = new Pass();
			pass.setOwnerNo(ownerNo);
			pass.setPassNo(Integer.parseInt(request.getParameter("passno"+i)));
			pass.setPassName(request.getParameter("passname"+i));
			pass.setPassStatus(1);
			pass.setPassPrice(Integer.parseInt(request.getParameter("passprice"+i)));
			pass.setPassMonth(Integer.parseInt(request.getParameter("passmonth"+i)));
			pass.setPauseCount(Integer.parseInt(request.getParameter("pausecount"+i)));
			pass.setPauseDate(Integer.parseInt(request.getParameter("pausedate"+i)));
			pass.setRemarks(request.getParameter("remarks"+i));
			passes.add(pass);
		}
		
		//전체 파라미터를 검색해서 원하는 키워드의 파라미터만 골라내서 활용하는 방법
		Enumeration<String> paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();
			if(name.contains("equip_no_")) {
				int eNo = Integer.parseInt(name.split("_", 3)[2]);
				int eCount = Integer.parseInt(request.getParameter(name));
				if(eCount >= 1) {
					gymEquip = new GymEquip();
					gymEquip.setOwnerNo(ownerNo);
					gymEquip.setEquipNo(eNo);
					gymEquip.setEquipCount(eCount);
					gymEquips.add(gymEquip);
				}
			}			
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
			
			service.gymRegist(gym, passes, gymEquips);
			
			request.setAttribute("status", 2);
			resultMsg = "gym등록성공";
			
		} catch (FindException e1) {
			e1.printStackTrace();
			request.setAttribute("status", 3);
			resultMsg = "gym등록실패";
		} catch(AddException e) {
			e.printStackTrace();
			request.setAttribute("status", 3);
			resultMsg = "gym등록실패";
		}
		
		request.setAttribute("msg", resultMsg);

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}

}
