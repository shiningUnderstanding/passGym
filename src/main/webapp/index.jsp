<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link rel="icon" href="./images/logo.png">
<title>index.jsp</title>
<link rel="stylesheet" href="./css/header.css" />
<link rel="stylesheet" href="./css/index.css" />
<link rel="stylesheet" href="./css/footer.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function() {
		/*--메뉴가 클릭되었을 때 START*/
		let $menuObj = $(".link");
		
		$menuObj.click(function() {
			let menuHref = $(this).attr("href");
			let ajaxUrl = "";
			let ajaxMethod = "";
			switch (menuHref) {
			case "login.jsp":
				ajaxUrl = menuHref;
				ajaxMethod = "get";
				$("section").empty();
				$("section").load(ajaxUrl, function(responseText, textStatus, jqXHR){
					if(jqXHR.status != 200){
						alert('응답실패:' + jqXHR.status);
					}
				});
				return false;

			case "mypage":
				//AJAX요청, 응답
				ajaxUrl = menuHref;
				ajaxMethod = "get";
				let userNo = 2;
				$("section").empty();
				//( String responseText, String textStatus, jqXHR jqXHR)
				$.ajax({
					url : ajaxUrl,
					method : ajaxMethod,
					data : {
						userNo : userNo
					}, 
					success : function(responseData) {
						$("section").html(responseData);
					},
					error : function(xhr) {
						alert("응답실패 status : " + xhr.status);
					},
				});
				return false;
				
			case "logout":
				ajaxUrl = menuHref;
				$.ajax({
					url: ajaxUrl,
					success: function(){
						location.href="./index.jsp";
					},
					error: function(xhr){
						alert('응답실패: ' + xhr.status);
					}
				});
				return false;
			}
		});
		/*--메뉴가 클릭되었을 때 END*/
	});
</script>
</head>


<body>
	<header>
		<jsp:include page="./menu.jsp" />
	</header>
	<section class="section">
		<div class="gymList">
			<div class="gymList__title">추천업체</div>
			<div class="gymList__details">
				<div class="gymList__detail">
					<ul>
						<li>헬스장 사진</li>
						<li>헬스장 설명</li>
					</ul>
				</div>
				<div class="gymList__detail">
					<ul>
						<li>헬스장 사진</li>
						<li>헬스장 설명</li>
					</ul>
				</div>
				<div class="gymList__detail">
					<ul>
						<li>헬스장 사진</li>
						<li>헬스장 설명</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="gymList">
			<div class="gymList__title">주변업체</div>
			<div class="gymList__details">
				<div class="gymList__detail">
					<ul>
						<li>헬스장 사진</li>
						<li>헬스장 설명</li>
					</ul>
				</div>
				<div class="gymList__detail">
					<ul>
						<li>헬스장 사진</li>
						<li>헬스장 설명</li>
					</ul>
				</div>
				<div class="gymList__detail">
					<ul>
						<li>헬스장 사진</li>
						<li>헬스장 설명</li>
					</ul>
				</div>
				<div class="gymList__detail">
					<ul>
						<li><div>헬스장 사진</div></li>
						<li>헬스장 설명</li>
					</ul>
				</div>
				<div class="gymList__detail">
					<ul>
						<li>헬스장 사진</li>
						<li>헬스장 설명</li>
					</ul>
				</div>
				<div class="gymList__detail">
					<ul>
						<li>헬스장 사진</li>
						<li>헬스장 설명</li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	<footer class="footer">
		<%@ include file="./footer.jsp"%>
	</footer>
</body>
</html>
