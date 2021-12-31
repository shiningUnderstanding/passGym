package com.passgym.user.control;

import java.io.IOException;
import java.util.Collection;

import com.passgym.exception.ModifyException;
import com.passgym.user.service.UserService;
import com.passgym.user.vo.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class EditUserSaveServlet
 */
@WebServlet("/editusersave")
@MultipartConfig
public class EditUserSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "jsonresult.jsp";
		String resultMsg = "";
		//session객체 받아오기 userNo, userId
		HttpSession session = request.getSession();
		User sessionUser = (User)session.getAttribute("userLoginInfo");
		
		String saveDirectory = "c:\\java\\files";
   		try {
//   		    Part part = request.getPart("f");
//   		    
//   		   	String fileName = part.getSubmittedFileName();
//   		   	System.out.println(fileName);
//		    part.write(saveDirectory+"\\" + fileName);
		    Collection<Part> parts = request.getParts();
		    
		    for(Part part:parts) {
		    	if("photo".equals(part.getName())) {
		    		if(part.getSize() == 0) {
		    			break;
		    		}
		    		System.out.println(part.getSize());
		    		String submittedFileName = part.getSubmittedFileName();
		    		int extIndex = submittedFileName.lastIndexOf('.');
		    		String ext = submittedFileName.substring(extIndex);
		    		String fileName = sessionUser.getUserNo() + ext;
		    		//String fileName = part.getSubmittedFileName();
	   		   		System.out.println(fileName);
	   		   	    part.write(saveDirectory+"\\" + fileName);
	   		   	    break;
		    	}
		    }
		    
   		} catch (Exception e) {
   		    e.printStackTrace();
   		}
		
		

		//request속성 받아오기
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String phoneNo = request.getParameter("phoneNo");
		String zipcode = request.getParameter("zipcode");
		String addr = request.getParameter("addr");
		String addrDetail = request.getParameter("addrDetail");
		
		//바뀐 유저 정보
		User user = new User();
		user.setUserNo(sessionUser.getUserNo());
		user.setId(sessionUser.getId());
		user.setName(name);
		user.setPwd(pwd);
		user.setPhoneNo(phoneNo);
		user.setZipcode(zipcode);
		user.setAddr(addr);
		user.setAddrDetail(addrDetail);
	
		UserService service = UserService.getInstance();
		try {
			service.editUser(user);
			request.setAttribute("status", 1);
		} catch (ModifyException e) {
			e.printStackTrace();
			request.setAttribute("status", 0);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
