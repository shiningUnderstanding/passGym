package com.passgym.equip.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.passgym.equip.service.EquipService;
import com.passgym.equip.vo.Equip;
import com.passgym.exception.FindException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/equiplist")
public class EquipListSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		List<Equip> list = new ArrayList<>();
		EquipService service = new EquipService();
		
		try {
			list = service.findEquipList();
		} catch (FindException e) {
			e.printStackTrace();
		}
		
//		list.add(new Equip(1,"스미스머신"));
//		list.add(new Equip(2,"레그익스프레스"));
//		list.add(new Equip(3,"레그익스텐션"));
//		list.add(new Equip(4,"로어암"));
//		list.add(new Equip(5,"벤치"));
//		list.add(new Equip(6,"바벨"));
//		list.add(new Equip(7,"덤벨"));
		
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("equiplistresult.jsp");
		rd.forward(request, response);

	}

}
