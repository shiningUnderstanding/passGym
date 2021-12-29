<%@page import="com.passgym.gym.vo.Gym"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Gym> gymList = (List) request.getAttribute("gymList");
%>
<link rel="stylesheet" href="./css/gymlist.css">
<script>
	$(function() {
		/*---헬스장 상세 클릭 START---*/
		gymDetail();
		/*---헬스장 상세 클릭 END---*/
	});
</script>
<div class="gymList">
	<div class="gymList__title">주변업체</div>
	<div class="gymList__details">
		<%
		for (Gym g : gymList) {
			int ownerNo = g.getOwnerNo();
			String name = g.getName();
			String addr = g.getAddr();
			double avgStar = g.getAvgStar();
			double distance = g.getDistance();
		%>
		<div class="gymList__detail" id="gym<%=ownerNo%>">
			<ul>
				<li><img src="./images/gym/<%=ownerNo%>.jpg"></li>
				<li class="gymlist__infos">
					<div class="gymlist__info-top">
						<div class="gymlist__name"><%=name%></div>
						<%
						if (g.getAvgStar() == 0) {
						%>
						<div class="gymlist__avgStar">★</div>
						<%
						} else {
						%>
						<div class="gymlist__avgStar">
							★<%=g.getAvgStar()%></div>
						<%
						}
						%>
					</div>
					<div class="gymlist__addr"><%=addr%></div>
					<div class="gymlist__distance"><%=distance%>Km
					</div>
				</li>
			</ul>
		</div>
		<%
		}
		%>
	</div>
</div>