<%@page import="com.passgym.userqna.vo.UserQna"%>
<%@page import="com.passgym.zzim.vo.Zzim"%>
<%@page import="com.passgym.gympass.vo.GymPass"%>
<%@page import="com.passgym.user.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
User user = (User) request.getAttribute("user");
%>
<link rel="stylesheet" href="./css/mypage.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function() {
		//----------------zzim삭제 시작------------------
		let $zzimBtnObj = $(".zzim__btn");
		
		$zzimBtnObj.click(function(){
			ajaxUrl = "removezzim";
			ajaxMethod = "post";
			let ownerNo = $(this).parent().attr("id");
			$.ajax({
				url : ajaxUrl,
				method : ajaxMethod,
				data : {
					ownerNo : ownerNo
				}, 
				success : function(responseObj) {
					console.log("응답성공");
					if(responseObj.status == 1){
						console.log("삭제성공");
						$("#" + ownerNo).parent().remove();
						//$("#mypageLink").trigger("click");
					}else{
						alert(responseObj.status);
					}
				},
				error : function(xhr) {
					alert("응답실패 status : " + xhr.status);
				},
			});
			
			return false;
		})
		//----------------zzim삭제  끝-------------------
		//----------------qna내용보기 시작------------------
		let $qnaContentObj = $(".qna__contentBtn");
		
		$qnaContentObj.click(function(){
			$(this).parent().siblings().toggle();
			if($(this).children().html() == "내용보기"){
				$(this).children().html("내용감추기");
			}else{
				$(this).children().html("내용보기");
			}
		})
		//----------------qna내용보기  끝-------------------
	});
</script>
<div class="profile_title">my profile</div>
<div class="profile">
	<div class="profile__img">
		<img src="./images/user/<%=user.getUserNo()%>.jpg" />
	</div>
	<div class="profile__info">
		<span>이름 : <%=user.getName()%></span> <span>이메일 : <%=user.getId()%></span>
		<span>주소 : <%=user.getAddr()%> (<%=user.getZipcode()%>)
		</span> <span>상세주소 : <%=user.getAddrDetail()%></span>
	</div>
	<div class="profile__edit">
		<button class="profile__editBtn">수정</button>
	</div>
</div>

<div class="gympass__title">결제한 헬스장 정보</div>
<div class="gympass">
	<%
	for (GymPass gp : user.getGymPasses()) {
		String status = null;
		if (gp.getStatus() == 0) {
			status = "사용중";
		} else if (gp.getStatus() == 1) {
			status = "사용대기중";
		} else if (gp.getStatus() == 2) {
			status = "일시정지";
		} else if (gp.getStatus() == 3) {
			status = "만료됨";
		}
	%>
	<div class="gympass__list">
		<div class="gympass__detail">
			<div class="gympass__infos">
				<div class="gympass__info-top">
					<div class="gympass__no">
						no.<%=gp.getPaymentNo()%></div>
					<div class="gympass__status"><%=status%></div>
				</div>
				<div class="gympass__info-mid">
					<div class="gympass__gym">
						<div class="gympass__gymName"><%=gp.getPass().getGym().getName()%></div>
						<div class="gympass__passName"><%=gp.getPass().getPassName()%></div>
					</div>
					<%
					if (gp.getStar().getStar() == 0) {
					%>
					<div class="star"><a id="1" href="mypage">☆</a><a id="2" href="mypage">☆</a><a id="3" href="mypage">☆</a><a id="4" href="mypage">☆</a><a id="5" href="mypage">☆</a> 별점을 주세요</div>
					<%
					} else {
					%>
					<div class="star">
						★<%=gp.getPass().getGym().getAvgStar()%></div>
					<%
					}
					%>
				</div>
				<div class="gympass__info-bot">
					<div class="gympass__date"><%=gp.getStartDate()%>
						~
						<%=gp.getEndDate()%></div>
					<div class="remain">
						남은 일수 :
						<%=gp.getRemain()%></div>
				</div>
			</div>
			<div class="gympass__btns">
				<button class="gympass__btn">연장</button>
				<button class="gympass__btn">일시정지</button>
			</div>
		</div>
	</div>
	<%
	}
	%>

</div>
<div class="zzim__title">찜한 헬스장 정보</div>
<div class="zzim">
	<%
	for (Zzim z : user.getZzims()) {
	%>
	<div class="zzim__list">
		<div id="<%=z.getGym().getOwnerNo() %>" class="zzim__detail">
			<div class="zzim__gymImg">
				<img src="./images/gym/<%=z.getGym().getOwnerNo()%>.jpg" />
			</div>
			<div class="zzim__infos">
				<div class="zzim__info-top">
					<div class="zzim__gymName"><%=z.getGym().getName()%></div>
					<%
					if (z.getGym().getAvgStar() == 0) {
					%>
					<div class="zzim__star">★</div>
					<%
					} else {
					%>
					<div class="zzim__star">
						★<%=z.getGym().getAvgStar()%></div>
					<%
					}
					%>
				</div>
				<div class="zzim__gymPhone"><%=z.getGym().getPhoneNo()%></div>
				<div class="zzim__gymAddr"><%=z.getGym().getAddr()%>(<%=z.getGym().getZipcode()%>)
				</div>
				<div class="zzim__gymAddrDetail"><%=z.getGym().getAddrDetail()%></div>
			</div>
			<div class="zzim__btn">
				<button class="zzim__deleteBtn">삭제</button>
			</div>
		</div>
	</div>
	<%
	}
	%>

</div>
<div class="qna__title">1 : 1 문의</div>
<div class="qna">
	<%
	for (UserQna uq : user.getUserQnas()) {
	%>
	<div class="qna__list">
		<div class="qna__detail">
			<div class="qna_infos">
				<div class="qna__info-show">
					<div class="qna__detailTitle">
						<span>제목 : </span><%=uq.getTitle()%>
					</div>
					<div class="qna__date">
						<span>문의 날짜 : </span><%=uq.getQnaDate()%>
					</div>
					<div class="qna__replyStatus">
						<%
						if (uq.getReplyStatus() == 0) {
						%>
						<span>상태 : </span>답변대기
						<%
						} else {
						%>
						<span>상태 : </span>답변완료
						<%
						}
						%>
					</div>
					<div class="qna__contentBtn">
						<button class="contentBtn">내용보기</button>
					</div>
				</div>
				<div class="qna__info-hidden">
					<div class="qna_content">
						<span>문의 내용 : </span>
						<%=uq.getContent()%>
					</div>
					<div class="qna__reply">
						<%
						if (uq.getReplyStatus() == 0) {
						%>
						<span>답변 내용 : </span>아직 문의를 처리하지 못하였습니다.
						<%
						} else {
						%>
						<span>답변 내용 : </span><%=uq.getReply()%>
						<%
						}
						%>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%
	}
	%>
</div>
<div class="question">
	<button class="question__btn">문의하기</button>
</div>