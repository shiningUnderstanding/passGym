<%@page import="com.passgym.user.vo.User" %>
<%@page import="com.passgym.owner.vo.Owner" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="login">
	<div class="login__container">
	<%
	User u = (User)session.getAttribute("userLoginInfo");
	Owner o = (Owner)session.getAttribute("ownerLoginInfo");
	if(u == null || o == null) {
	%>
		<a class="loginBtn link" href="">로그인</button>
	<%
	} else {
	%> 
		<%=u.getName() %>님 반갑습니다. <a class="loginBtn link" href="logout">로그아웃</button>
		<a class="myPageBtn link" href="mypage">마이페이지</a> 
		<%} %>
	</div>
</div>
<nav class="nav">
	<div class="nav__container">
		<!--머리말-->
		<h1 class="nav__main">
			<!--로고이미지-->
			<a href="index.html"><span>패스짐</span><img class="nav-bar__logo link"
				src="./images/logo.png" /></a>
		</h1>
		<div class="nav-bar__links">
			<!--메뉴-->
			<a class="link" href="">홈</a> <a class="link" href="">지도</a> <a class="link" href="">고객센터</a>
		</div>
	</div>
</nav>