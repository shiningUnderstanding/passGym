<%@page import="com.passgym.user.vo.User"%>
<%@page import="com.passgym.gym.vo.Gym"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
User u = (User) request.getAttribute("userLoginInfo");
List<Gym> gymList = (List) request.getAttribute("gymList");
List<Gym> zzimList = (List) request.getAttribute("zzimList");
List<Gym> avgStarList = (List) request.getAttribute("avgStarList");
%>
<link rel="stylesheet" href="./css/gymlistresult.css">
<script src="./js/gymlistresult.js"></script>
<script>
	$(function() {
		/*---헬스장 상세 클릭 START---*/
		gymDetail();
		/*---헬스장 상세 클릭 END---*/
	});
</script>
<div class="gymlist">
	<div class="gymlist__title">찜한 헬스장</div>
	<div class="gymlist__details">
		<%
		if (zzimList != null) {//찜한 헬스장이 있다면
			for (Gym g : zzimList) {
				int ownerNo = g.getOwnerNo();
				String name = g.getName();
				String addr = g.getAddr();
				double avgStar = g.getAvgStar();
				double distance = g.getDistance();
		%>
		<div class="gymlist__detail" id="<%=ownerNo%>">
			<a href="javascript:void(0)">
				<ul>
					<li><img src="./images/gym/<%=ownerNo%>.jpg"></li>
					<li class="gymlist__infos">
						<div class="gymlist__info-top">
							<div class="gymlist__name"><%=name%></div>
							<%
							if (g.getAvgStar() == 0) {//평점이 0점이면 별만 표시
							%>
							<div class="gymlist__avgStar">★0.0</div>
							<%
							} else {//평점이 있으면 별 + 평점표시
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
			</a>
		</div>
		<%
		}
		} else {//찜한 헬스장이 없다면
		%>
		<div>찜한 헬스장이 없습니다</div>
		<%
		}
		%>
	</div>
	<div class="gymlist__title">주변 헬스장</div>
	<div class="gymlist__details">
		<%
		for (Gym g : gymList) {
			int ownerNo = g.getOwnerNo();
			String name = g.getName();
			String addr = g.getAddr();
			double avgStar = g.getAvgStar();
			double distance = g.getDistance();
		%>
		<div class="gymlist__detail" id="<%=ownerNo%>">
			<a href="javascript:void(0)" >
				<ul>
					<li><img src="./images/gym/<%=ownerNo%>.jpg"></li>
					<li class="gymlist__infos">
						<div class="gymlist__info-top">
							<div class="gymlist__name"><%=name%></div>
							<%
							if (g.getAvgStar() == 0) {
							%>
							<div class="gymlist__avgStar">★0.0</div>
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
			</a>
		</div>
		<%
		}
		%>
	</div>
	<div class="gymlist__title">별점순</div>
	<div class="gymlist__details">
		<%
		for (Gym g : avgStarList) {
			int ownerNo = g.getOwnerNo();
			String name = g.getName();
			String addr = g.getAddr();
			double avgStar = g.getAvgStar();
			double distance = g.getDistance();
		%>
		<div class="gymlist__detail" id="<%=ownerNo%>">
			<a href="javascript:void(0)">
				<ul>
					<li><img src="./images/gym/<%=ownerNo%>.jpg"></li>
					<li class="gymlist__infos">
						<div class="gymlist__info-top">
							<div class="gymlist__name"><%=name%></div>
							<%
							if (g.getAvgStar() == 0) {
							%>
							<div class="gymlist__avgStar">★0.0</div>
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
			</a>
		</div>
		<%
		}
		%>
	</div>
</div>