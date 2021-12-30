package com.passgym.user.control;

import java.io.File;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.passgym.exception.ModifyException;
import com.passgym.user.service.UserService;
import com.passgym.user.vo.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class EditUserSaveServlet
 */
@WebServlet("/editusersave")
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
			
			//file upload
			DiskFileItemFactory fileItemFactory;
			fileItemFactory = new DiskFileItemFactory();
			String saveDirectory = "c:\\java\\passGym\\passgym\\src\\main\\webapp\\images\\user";
			File f = new File(saveDirectory);
			fileItemFactory.setRepository(f);
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);	
			request.setAttribute("status", 1);
		} catch (ModifyException e) {
			e.printStackTrace();
			request.setAttribute("status", 0);
		}
		
		
	}

}
