<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.passgym.ownerqna.vo.OwnerQna"%>
<%@page import="com.passgym.owner.vo.Owner"%>
<link rel="stylesheet" href="./css/qnahome.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>



<div class="qna__title">1 : 1 문의</div>
 
<%
Owner owner = (Owner) request.getAttribute("owner");
%>
<div class="qna__title">1 : 1 문의</div>
<script>
//----------------ownerqna 내용보기 시작------------------
let $qnaContentObj = $(".qna__contentBtn");

$qnaContentObj.click(function() {
	$(this).parent().siblings().toggle();
	if ($(this).children().html() == "내용보기") {
		$(this).children().html("내용감추기");
	} else {
		$(this).children().html("내용보기");
	}
})
//----------------ownerqna 내용보기  끝-------------------
//---------------ownerqna 작성버튼 클릭 시작------------------
let $qnaBtn = $(".question__btn");

$qnaBtn.click(function() {
	let ajaxUrl = "ownerqnaform.html";
	let ajaxMethod = "get";
    $("section").empty();
    $("section").load(ajaxUrl, function (responseText, textStatus, jqXHR) {
      if (jqXHR.status != 200) {
        alert("응답실패:" + jqXHR.status);
      }
    });
    return false;
})
//---------------ownerqna 작성버튼 클릭끝-----------------

</script>

<div class="qna">
	<% for (OwnerQna  op : owner.getOwnerQnas()) { %>
	<div class="qna__list">
		<div class="qna__detail">
			<div class="qna_infos">
				<div class="qna__info-show">
					<div class="qna__detailTitle">
						<span>제목 : </span> <%=op.getTitle()%>
					</div>
					<div class="qna__date">
						<span>문의 날짜 : </span> <%=op.getQnaDate()%>
					</div>
					<div class="qna__replyStatus">
						<% if (op.getReplyStatus() == 0) { %> <span>상태 : </span>답변대기 <% }
						else { %> <span>상태 : </span>답변완료 <% } %>
					</div>
					<div class="qna__contentBtn">
						<button class="contentBtn">내용보기</button>
					</div>
				</div>
				<div class="qna__info-hidden">
					<div class="qna_content">
						<span>문의 내용 : </span> <%=op.getContent()%>
					</div>
					<div class="qna__reply">
						<% if (op.getReplyStatus() == 0) { %> <span>답변 내용 : </span>아직 문의를
						처리하지 못하였습니다. <% } else { %> <span>답변 내용 : </span>
						<%=op.getReply()%> <% } %>
					</div>
				</div>
			</div>
		</div>
	</div>
	<% } %>
</div>
<div class="question">
	<button class="question__btn">문의하기</button>
</div>
