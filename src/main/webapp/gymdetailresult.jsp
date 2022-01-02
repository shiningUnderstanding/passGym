<%@page import="com.passgym.pass.vo.Pass"%>
<%@page import="java.util.List"%>
<%@page import="com.passgym.gym.vo.Gym"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Gym g = (Gym) request.getAttribute("gymDetail");
List<Pass> passes = (List<Pass>) request.getAttribute("passes");
int ownerNo = g.getOwnerNo();
String name = g.getName();
String phoneNo = g.getPhoneNo();
String zipcode = g.getZipcode();
String addr = g.getAddr();
String addrDetail = g.getAddrDetail();
String introduce = g.getIntroduce();
String notice = g.getNotice();
String operatingTime = g.getOperatingTime();
String operatingProgram = g.getOperatingProgram();
String extraService = g.getExtraService();
String etc = g.getEtc();
int totalStar = g.getTotalStar();
int totalMember = g.getTotalMember();
double avgStar = g.getAvgStar();
double lat = g.getLat();
double lon = g.getLon();
double distance = g.getDistance();
%>
<head>
<meta charset="UTF-8" />
<link rel="icon" href="./images/logo.png" />
<title><%=name%></title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="./css/header.css" />
<link rel="stylesheet" href="./css/index.css" />
<link rel="stylesheet" href="./css/footer.css" />
<link rel="stylesheet" href="./css/gymdetailresult.css">
<script src="./js/menu.js"></script>
<script src="./js/gymdetailresult.js"></script>
<script>
	$(function() {
		/*--메뉴가 클릭되었을 때 START*/
		menuClick();
		/*--메뉴가 클릭되었을 때 END*/
		/*---옵션 선택 버튼 클릭되었을 때 START---*/
		//optionClick();
		/*---옵션 선택 버튼 클릭되었을 때 END---*/
		/*---결제 버튼 클릭되었을 때 START---*/
		paymentClick();
		/*---결제 버튼 클릭되었을 때 END---*/
	});
</script>
</head>
<body>
	<header>
		<jsp:include page="./menu.jsp" />
	</header>
	<section class="section">
		<div class="gymdetail">
			<div class="gymdetail__header">
				<div class="gymdetail__img">
					<img src="./images/gym/<%=ownerNo%>.jpg">
				</div>
				<div class="gymdetail__info">
					<div class="gymdetail__name">
						<%=name%>
					</div>
					<div class="gymdetail__phoneNo">
						<%=phoneNo%>
					</div>
					<div class="gymdetail__addr">
						<%=addr + ", " + addrDetail%>
					</div>
					<div class="gymdetail__avgStar">
						★<%=avgStar%>
					</div>
					<div class="gymdetail__distance">
						<%
						if (lat == 0 && lon == 0) {
						%>
						서울역기준<%=distance%>Km
						<%
						} else {
						%>
						현재위치기준<%=distance%>Km
						<%
						}
						%>
						<form class="gymdetail__option">
							<select class="gymdetail__option_choose" id="<%=ownerNo%>">
								<option value="option">옵션 선택</option>
								<%
								for (Pass p : passes) {
									int passNo = p.getPassNo();
									String passName = p.getPassName();
									int passPrice = p.getPassPrice();
								%>
								<option value="<%=passNo%>"><%=passName + " " + passPrice + "원"%></option>
								<%
								}
								%>
							</select>
						</form>
					</div>
					<div class="gymedetail__payment">
						<span class="btn_type">
							<button type="button" class="gymdetail__paymentBtn"
								onclick="paymentClick()">결제하기</button>
						</span>
					</div>
				</div>
			</div>
			<div class="gymdetail__body">
				<div class="gymdetail__introduce">
					<%=introduce%>
				</div>
				<hr>
				<div class="gymdetail__notice">
					<%=notice%>
				</div>
				<hr>
				<div class="gymdetail__operatingTime">
					<%=operatingTime%>
				</div>
				<hr>
				<div class="gymdetail__operatingProgram">
					<%=operatingProgram%>
				</div>
				<hr>
				<div class="gymdetail__extraService">
					<%=extraService%>
				</div>
				<hr>
				<div class="gymdetail__etc">
					<%=etc%>
				</div>
				<hr>
			</div>
		</div>
	</section>
	<footer class="footer"><%@include file="./footer.jsp"%></footer>
</body>