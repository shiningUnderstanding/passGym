<%@page import="com.passgym.gym.vo.Gym"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Gym g = (Gym)request.getAttribute("g");
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
<link rel="stylesheet" href="./css/gymdetailresult.css">
<script src="./js/gymdetail.js"></script>
<script>
$(function(){
	/*---결제 버튼 클릭되었을 때 START---*/
	paymentClick();
	/*---결제 버튼 클릭되었을 때 END---*/
});
</script>
<div class="gymdetail<%=ownerNo%>">
	<div class="gymdetail__header">
		<div class="gymdetail__img">
			<p>
				<img src="./images/gym/<%=ownerNo%>.jpg">
			</p>
		</div>
			
	</div>
</div>