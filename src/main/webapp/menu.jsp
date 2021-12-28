<%@page import="com.passgym.user.vo.User" %>
<%@page import="com.passgym.owner.vo.Owner" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="login_menu">
	<div class="login__container">
	<%
	User u = (User)session.getAttribute("userLoginInfo");//사용자로구인 된 경우
	Owner o = (Owner)session.getAttribute("ownerLoginInfo");//헬스장로그인된 경우 

	if(u == null && o == null) {
	%>
		<a class="loginBtn link" href="login.jsp">로그인</a>
	<%
	}else if(u == null){ //헬스장로그인된 경우 
	} else { //사용자 로그인 된 경우
	%> 
		<%=u.getName() %>님 반갑습니다. <a class="loginBtn link" href="logout">로그아웃</a>
		<a class="mypageBtn link" href="mypage">마이페이지</a> 
		<%} %>

	</div>
</div>
<nav class="nav">
	<div class="nav__container">
		<!--머리말-->
		<h1 class="nav__main">
			<!--로고이미지-->
			<a href="index.jsp"><span>패스짐</span><img class="nav-bar__logo link"
				src="./images/logo.png" /></a>
		</h1>
		<div class="nav-bar__links">
			<!--메뉴-->
			<a class="link" href="">홈</a> <a class="link" href="">지도</a> <a class="link" href="">고객센터</a>
		</div>
	</div>
</nav>