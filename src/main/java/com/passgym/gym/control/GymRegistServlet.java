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
      

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Owner sessionOwner = (Owner)session.getAttribute("signupInfo");
		
		int ownerNo = sessionOwner.getOwnerNo();
		GymService service = new GymService();
		Gym gym;
		
		Pass pass = new Pass();
		List<Pass> passes = new ArrayList<Pass>();
		String iSend = request.getParameter("i");
		
		GymEquip gymEquip = new GymEquip();
		List<GymEquip> gymEquips = new ArrayList<GymEquip>();
		
		
		//전체 pass의 개수를 html 태그로 추가한 후 serialize해서 정보를 받아오는 방법
		for(int i = 0; i < Integer.parseInt(iSend) + 1; i++) {
			passes.add(pass);
			Pass passComponent = passes.get(i);
			passComponent.setOwnerNo(ownerNo);
			System.out.println(Integer.parseInt(request.getParameter("passno"+i)));
			passComponent.setPassNo(Integer.parseInt(request.getParameter("passno"+i)));
			passComponent.setPassName(request.getParameter("passname"+i));
			passComponent.setPassStatus(1);
			passComponent.setPassPrice(Integer.parseInt(request.getParameter("passprice"+i)));
			System.out.println(Integer.parseInt(request.getParameter("pausecount"+i)));
			passComponent.setPassMonth(Integer.parseInt(request.getParameter("passmonth"+i)));
			passComponent.setPauseCount(Integer.parseInt(request.getParameter("pausecount"+i)));
			passComponent.setPauseDate(Integer.parseInt(request.getParameter("pausedate"+i)));
		}
		
		//전체 파라미터를 검색해서 원하는 키워드의 파라미터만 골라내서 활용하는 방법
		Enumeration<String> paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();
			if(name.contains("equip_no_")) {
				int eNo = Integer.parseInt(name.split("_", 3)[2]);
				int eCount = Integer.parseInt(request.getParameter(name));
				if(eCount >= 1) {
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
			
			request.setAttribute("ownerStatus", 1);
			request.setAttribute("status", 2);
			resultMsg = "gym등록성공";
			
		} catch (FindException e1) {
			e1.printStackTrace();
			request.setAttribute("ownerStatus", 0);
			request.setAttribute("status", 2);
			resultMsg = "gym등록실패";
		} catch(AddException e) {
			e.printStackTrace();
			request.setAttribute("ownerStatus", 0);
			request.setAttribute("status", 2);
			resultMsg = "gym등록실패";
		}
		
		request.setAttribute("msg", resultMsg);

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}

}
