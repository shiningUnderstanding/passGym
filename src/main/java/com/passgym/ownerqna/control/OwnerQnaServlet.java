package com.passgym.ownerqna.control;

import java.io.IOException;

import com.passgym.exception.AddException;
import com.passgym.owner.vo.Owner;
import com.passgym.ownerqna.service.OwnerQnaService;
import com.passgym.ownerqna.vo.OwnerQna;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ownerqna")
public class OwnerQnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = "jsonresult.jsp";
		String resultMsg = "";
		
		//session 객체 받아오기 
		HttpSession session = request.getSession();
		Owner sessionOwner = (Owner)session.getAttribute("OwnerLoginInfo");
		
		//파라미터 값 받아오기 
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		OwnerQna ownerQna = new OwnerQna();
		ownerQna.setTitle(title);
		ownerQna.setContent(content);
		ownerQna.setOwnerNo(sessionOwner.getOwnerNo());
		
		//서비스 받아오기 
		OwnerQnaService service = OwnerQnaService.getInstance();
		
		//비즈니스 로직 구현 
		try {
			service.addOnwerQna(ownerQna);
			
			
			//결과값 설정 
			request.setAttribute("status", 1);
			resultMsg = "문의 성공";
		} catch (AddException e) {
			
			e.printStackTrace();
			request.setAttribute("status", 0);
			resultMsg= "문의 실패";
		}
	
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
