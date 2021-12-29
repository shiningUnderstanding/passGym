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
<script src="./js/main.js"></script>

<script src="./js/menu.js"></script>
<script src="./js/gymlist.js"></script>
<script>
	$(function() {
		/*---index load될 때 START---*/
		gymList();
		//getLocation();
		/*---index load될 때 END---*/
		/*--메뉴가 클릭되었을 때 START*/
		menuClick();
		/*--메뉴가 클릭되었을 때 END*/
	});
</script>
</head>


<body>
	<header>
		<jsp:include page="./menu.jsp" />
	</header>
	<section class="section">
		
	</section>
	<footer class="footer">
		<%@include file="./footer.jsp"%>
	</footer>
</body>
</html>
