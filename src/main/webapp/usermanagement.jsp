<%@page import="com.passgym.payment.vo.Payment"%>
<%@page import="com.passgym.user.vo.User"%>
<%@page import="com.passgym.gympass.vo.GymPass"%>
<%@page import="com.passgym.pass.vo.Pass"%>
<%@page import="java.util.List"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--
<% String msg = (String)request.getAttribute("msg"); 
	if(msg !=null){
%>		
 <script>alert('<%=msg%>');</script>}else {
		List<Pass> passes = (List) request.getAttribute("passes");
		for (Pass p : passes) {
			String passName = p.getPassName();//이용권이름 

			//이용권을 구매한 회원목록 
			for (GymPass gp : p.getGympasses()) {
				User u = gp.getUser();
				Payment pay = gp.getPayment();
				//System.out.println(u.getId() + ":" + u.getName() + ":" + pay.getPaymentNo() + ":" + pay.getPaymentPrice());
			}
		} //end for
	} //end ifif
%>  

 --%>
 
 
<title>회원관리</title>
<link rel="stylesheet" href="./css/usermanagement.css" />
</head>
<body>
	<div class="title">
		<div>
			<b>회 원 관 리</b>
		</div>
		<!--구매이용권정보-->
	</div>

	<%
	String msg = (String) request.getAttribute("msg");
	if (msg != null) {
	%>
	<script>alert('<%=msg%>
		');
	</script>
	<%
	return;
	}
	//정상처리 
	List<Pass> passes = (List) request.getAttribute("passes");
	%>
	<div class="form">
			<%
			for (Pass p : passes) {
				String passName = p.getPassName();  //이용권이름
				System.out.println(p);
			%> 

		<!--회원 기본정보-->
		<table class="tb__1" border="1">
			<caption class="pass__name">
				<b><%=passName%></b> <!-- 회원권 -->
			</caption>
			<tr class="th__content__1">
				<!-- 제목 -->
				<th>회원권 가격</th>
				<th>회원권 생성일자</th>
				<th>회원권 상태</th>
				<th>회원권 개월수</th>
				<th>일시정지 가능횟수</th>
				<th>회원권 가능일수</th>
				<th>비고</th>
			</tr>
			<tr class="td__content__1">
				<!-- 내용 -->
				<td><%=p.getPassPrice()%></td>
				<td><%=p.getPassDate()%></td>
				<td><%=p.getPassStatus()%></td>
				<td><%=p.getPassMonth()%></td>
				<td><%=p.getPauseCount()%></td>
				<td><%=p.getPauseDate() %></td>
				<td><%=p.getRemarks()%></td>
			</tr>
			
		</table>

		<!--이용권정보-->
		<table class="tb__2" border="1">
			<tr class="th__content__2">
				<th>회원번호</th>
				<th>이름</th>
				<th>아이디</th>
				<th>휴대혼 번호</th>
				<th>주소</th>
				<th>상세주소</th>
			</tr>
				<%
				//이용권을 구매한 회원목록 
				for (GymPass gp : p.getGympasses()) {
					User u = gp.getUser();
					Payment pay = gp.getPayment();
					//System.out.println(u.getId() + ":" + u.getName() + ":" + pay.getPaymentNo() + ":" + pay.getPaymentPrice());
				%>
			<tr class="td__content__2">
				<!-- 내용 -->
				<td><%=u.getUserNo()%></td>
				<td><%=u.getName()%></td>
				<td><%=u.getId()%></td>
				<td><%=u.getPhoneNo()%></td>
				<td><%=u.getAddr()%></td>
				<td><%=u.getAddrDetail()%></td>
			</tr>
			
			<%
				} //end 이용권을 구매한 회원목록
				%>
		</table>
		<br>
		<hr>		  	
		<%
} //end for passes
%>
 </div>
</body>
</html>