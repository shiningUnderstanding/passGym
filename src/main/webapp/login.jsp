<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>passGym : 로그인</title>
<meta charset="UTF-8">
<style>
body {
	font-family: "Noto Sans kr", sans-serif;
}

.login {
	width: 400px;
	max-width:100%;
	height: 500px;
	margin-bottom: 50px;
}
            
.login__body{
	width: 100%;
	height: 100%;
	display:flex;
	flex-direction: column;
	align-items:center;
}

.login__radioChk {
	width: 100%;
	display: flex;
	justify-content: center;
	color: darkslategray;
}

.login__radioChk label{
	margin: 0 20px;
	
}

.login__userChk, .login__ownerChk {
	margin: 10px
}

.login__id, .login__pwd {
	display: flex;
	flex-direction: center;
	width: 312px !important;
	height: 48px;
	margin-top: 0 !important;
	border: 1px solid darkslategray;
}

.login__id:focus, .login__pwd:focus {
	display: flex;
	flex-direction: center;
	width: 312px !important;
	height: 48px;
	margin-top: 0 !important;
	border: 2px solid darkslategray;
}

.login__submit {
	/* margin: 12px 0!important; */
	width: 312px;
	height: 48px;
	background-color: #f6f5ef;
	border: 1px solid #f6f5ef;
	border-radius: 4px;
	font-size: 16px;
	color: darkslategray;
	cursor: pointer;
}

.login__submit:hover {
	/* margin: 12px 0!important; */
	width: 312px;
	height: 48px;
	background-color: #dad9d6;
	border: 1px solid #dad9d6;
	border-radius: 4px;
	font-size: 16px;
	color: darkslategray;
	cursor: pointer;
}

.login__find {
	display: flex;
	justify-content: center;
}

.login__searchid:link, .login__searchpwd:link, .login__searchid:visited,
	.login__searchpwd:visited {
	background-color: white;
	border: none;
	cursor: pointer;
	width: 100px;
	color: darkslategray;
	text-align: center;
	text-decoration: none;
	margin: 10px;
}

.login__searchid:hover, .login__searchpwd:hover {
	background-color: white;
	border: none;
	cursor: pointer;
	width: 100px;
	color: darkslategray;
	text-decoration: underline;
	margin: 10px;
}

.user, .owner {
	background-color: #f6f5ef;
	border: 1px solid #f6f5ef;
	border-radius: 4px;
	font-size: 16px;
	color: darkslategray;
	cursor: pointer;
	margin: 10px;
}

.user:hover, .owner:hover {
	background-color: #dad9d6;
	border: 1px solid #dad9d6;
	border-radius: 4px;
	font-size: 16px;
	color: darkslategray;
	cursor: pointer;
}

.sns, .signup{
    display: flex;
    flex-direction: column;
    align-items: center;
}

.sns__line, .signup__line{
    position: relative;
    bottom: -8px;
    display: block;
    margin: 0;
    width: 100%;
    height: 1px;
    background-color: #f1f3f5;
    border: none;
}

.sns__title, .signup__title{
    padding: 0 8px;
    margin-bottom: 16px;
    font-size: 11px;
    line-height: 16px;
    letter-spacing: -.3px;
    color: #abb0b5;
    z-index: 11;
    background-color: #fff;
}

.sns__button, .singup__button{
    display: flex;
    margin: auto;
    -moz-column-gap: 12px;
    column-gap: 12px;
}

.login__kakao, .login__naver {
	margin: 10px;
	width: 50px;
	display: flex;
	justify-content: center;
	border-radius: 50%;
}

.signup {
	display: flex;
	justify-content: center;
	color: darkslategray;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="./js/login.js"></script>
<script>
            $(function(){
            	loginClick();
            	$('.user').click(function(){
            		let ajaxUrl = './usersignup.jsp';
            		$("section").load(ajaxUrl, function(responseText, textStatus, jqXHR){
    					if(jqXHR.status != 200){
    						alert('응답실패:' + jqXHR.status);
    					}
    				});
    				return false;
            	});

				// $('div.login__body div.signup>button.owner').click(function(){
            	// 	let ajaxUrl = './ownersignup.html';
            	// 	$("section").load(ajaxUrl, function(responseText, textStatus, jqXHR){
    			// 		if(jqXHR.status != 200){
    			// 			alert('응답실패:' + jqXHR.status);
    			// 		}
    			// 	});
    			// 	return false;
            	// });
            });
        </script>
</head>

<body>
	<div class="login">
		<!-- <div class="login__header">
				<header>
					<h1 class="login__main">
						로고이미지
						<a href="index.html">
						<span>패스짐</span>
						<img class="login__mainLogo" src="./images/logo.png"/>
						</a>
					</h1>
				</header>
			</div> -->
		<div class="login__body">
			<form class="login__form" id="loginform" method="post" action="">
				<div class="login__radioChk">
					<label><input type="radio" class="login__userChk"
						name="check" id="userChk" value="user">사용자</label> <label><input
						type="radio" class="login__ownerChk" name="check" id="ownerChk"
						value="owner">사업자</label>
				</div>

				<div class="login__input">
					<input type="email" class="login__id" name="id" required
						placeholder="이메일"><br> <input type="password"
						class="login__pwd" name="pwd" required placeholder="비밀번호"><br>
					<button type="submit" class="login__submit" value="로그인">로그인</button>
					<br>
        <div class="login__find">
				<!-- <button type="button" value="이메일/비밀번호 찾기">이메일/비밀번호 찾기</button> -->
				<a href="./findemail.html" class="login__searchid">이메일 찾기</a> <a
					href="./findpassword.html" class="login__searchpwd">비밀번호 찾기</a>
			</div>
			<br>
            <div class="sns">
				<hr class="sns__line">
                <span class="sns__title">간편로그인</span>
				<div class="sns__button">
					<a href=""> <img src="./images/kakao.png"
						class="login__kakao" alt="카카오톡으로 로그인"></a> <a href=""> <img
						src="./images/naver.png" class="login__naver" alt="네이버로 로그인"></a>
				</div>
            </div>
            <br>
            <div class="signup">
				<hr class="signup__line">
                <span class="signup__title">회원가입</span>
				<div class="signup__button">
					<!--  <button type="button" value="사용자용" onclick="location.href='./usersignup.jsp'">사용자 회원가입</button> -->
					<button type="button" value="사용자용" class="user">사용자 회원가입</button>
					<button type="button" class="owner" onClick="location.href='./ownersignup.html'">사업자 회원가입</button>
				</div>
            </div>
			</form>

			
	</div>
</body>
</html>