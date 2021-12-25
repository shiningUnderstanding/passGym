<%@page import="com.passgym.payment.vo.Payment"%>
<%@page import="com.passgym.user.vo.User"%>
<%@page import="com.passgym.gympass.vo.GymPass"%>
<%@page import="com.passgym.pass.vo.Pass"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String msg = (String)request.getAttribute("msg"); 
	if(msg !=null){
%>		<script>alert('<%=msg%>');</script>
<%	}else{
		List<Pass>passes = (List)request.getAttribute("passes");
	for(Pass p: passes){
		String passName = p.getPassName();//이용권이름 
		
		//이용권을 구매한 회원목록 
		for(GymPass gp: p.getGympasses()) {
			User u = gp.getUser();
			Payment pay = gp.getPayment();
//			System.out.println(u.getId() + ":" + u.getName() + ":" + pay.getPaymentNo() + ":" + pay.getPaymentPrice());
		}
	}//end for
}//end if
%>
