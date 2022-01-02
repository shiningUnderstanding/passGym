<%@page import="com.passgym.gym.vo.Gym"%>
<%@page import="com.passgym.paymentmethod.vo.PaymentMethod"%>
<%@page import="com.passgym.user.vo.User"%>
<%@page import="com.passgym.pass.vo.Pass"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
User u = (User)request.getAttribute("userLoginInfo");
Gym g = (Gym)request.getAttribute("gymDetail");
Pass pass = (Pass)request.getAttribute("pass");
PaymentMethod pm = (PaymentMethod)request.getAttribute("paymentMethod");
%>

<div class="paymentpage">
	<div class="paymentpage__passInfo">
	
	</div>
	<div class="paymentpage__userInfo">
	
	</div>
	<div class="paymentpage__methodInfo">
	
	</div>
</div>