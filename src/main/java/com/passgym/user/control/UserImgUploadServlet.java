package com.passgym.user.control;

import java.io.File;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
		//file upload
		DiskFileItemFactory fileItemFactory;
		fileItemFactory = new DiskFileItemFactory();
		String saveDirectory1 = "..\\..\\..\\..\\..\\webapp\\images\\user";
		String saveDirectory = "c:\\java\\passGym\\passgym\\src\\main\\webapp\\images\\user";
		File f = new File(saveDirectory);
		fileItemFactory.setRepository(f);
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);	
	}

}
