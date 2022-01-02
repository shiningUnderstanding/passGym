package com.passgym.gym.control;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.passgym.owner.vo.Owner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/gymphotoregist")
@MultipartConfig(
		fileSizeThreshold = 1024*1024,
		maxFileSize = 1024*1024*50, //50메가
		maxRequestSize = 1024*1024*50*5 // 50메가 5개까지
		) 
public class GymPhotoRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Owner sessionOwner = (Owner) session.getAttribute("signupInfo");
		int ownerNo = sessionOwner.getOwnerNo();
		
		String savingDirectory = "C:\\java\\file"+ownerNo+"\\";
		File Folder = new File(savingDirectory);
		Folder.mkdir(); //해당 owner의 번호를 이름으로 가진 폴더 생성
		
		Part part = request.getPart("gymface");
		String filename = UUID.randomUUID().toString().replace("-", "");
		System.out.println(filename);
		String wholePath = savingDirectory+filename+".png";
		
		part.write(wholePath);
		
		session.removeAttribute("photoPath");
		session.setAttribute("photoPath", wholePath);
		response.sendRedirect("./gymregist.jsp");
	}

}
