package com.passgym.user.control;

import java.io.File;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.passgym.user.vo.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class UserImgUploadServlet
 */
@WebServlet("/userimgupload")
public class UserImgUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User sessionUser = (User)session.getAttribute("userLoginInfo");
		
		//file upload
		DiskFileItemFactory fileItemFactory;
		fileItemFactory = new DiskFileItemFactory();
		String saveDirectory1 = "..\\..\\..\\..\\..\\webapp\\images\\user" + sessionUser.getUserNo() + ".jpg";
		String saveDirectory = "c:\\java\\passGym\\passgym\\src\\main\\webapp\\images\\user";
		File f = new File(saveDirectory);
		fileItemFactory.setRepository(f);
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);	
	}

}
