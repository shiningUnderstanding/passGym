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

.modal__box{
	width:100%;
	height: 100%;
}

/* .login__header{
                width: 100px;
                text-align: center;
            }
            .login__mainLogo{
                width: 100%;
            } */
            
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

.login__hr {
	display: flex;
	flex-basis: 100%;
	align-items: center;
	color: darkslategray;
	font-size: 12px;
	margin: 8px 0px;
}

/* .hr{
	border: 1px darkslategray;
	width: 50%;
	height: 1px;
} */
/* .login__hr::before, .login__hr::after {
	content: "";
	flex-grow: 1;
	background-color: darkslategray;
	height: 1px;
	font-size: 0px;
	line-height: 0px;
	margin: 0px 16px;
} */

.login__sns {
	width: 50px;
	display: flex;
	justify-content: center;
	border-radius: 50%;
}

.login__line{
	position: relative;
	margin: 0!important;
	width: 100%;
	height: 1px;
	background-color: darkslategray;
}

hr{
	border: none;
	display: block;
}
.kakao, .naver {
	margin: 10px;
}

.signup {
	display: flex;
	justify-content: center;
	color: darkslategray;
}
/* .modal__background{
                position: fixed;
                top: 0;
                left: 0;
                bottom: 0;
                right: 0;
                background: rgba(0, 0, 0, 0.8);
            }

            .modal__box{
                position: absolute;
                top: 50%;
                left: 50%;
                background-color: white;
                display: flex;
                justify-content: center;
                align-items: center;
                border-radius: 10px;
                width: 400px;
                height: 100%;
            } */
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="./js/login.js"></script>
<script>
            $(function(){
            	loginClick();
            	//body > section > div > div > div > div.signin > button.user
            	$('div.login__body div.signup>button.user').click(function(){
            		let ajaxUrl = './usersignup.jsp';
            		$("section").load(ajaxUrl, function(responseText, textStatus, jqXHR){
    					if(jqXHR.status != 200){
    						alert('응답실패:' + jqXHR.status);
    					}
    				});
    				return false;
            	});
            });
        </script>
</head>

<body>
	<div class="login modal__background">
		<div class="modal__box">
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
					</div>
				</form>

				<div class="login__find">
					<!-- <button type="button" value="이메일/비밀번호 찾기">이메일/비밀번호 찾기</button> -->
					<a href="./findemail.html" class="login__searchid">이메일 찾기</a> <a
						href="./findpassword.html" class="login__searchpwd">비밀번호 찾기</a>
				</div>
				<div class="login__sns">
					<hr class="login__line">
					<span clsss="login__title">간편로그인</span>
					<div class="login__snsbutton">
					<a href=""> <img src="./images/kakao.png"
						class="login__sns kakao" alt="카카오톡으로 로그인"></a> <a href=""> <img
						src="./images/naver.png" class="login__sns naver" alt="네이버로 로그인"></a>
					</div>
				</div>
				<div class="login__hr">회원가입</div>
				<div class="signup">
					<!--  <button type="button" value="사용자용" onclick="location.href='./usersignup.jsp'">사용자 회원가입</button> -->
					<button type="button" value="사용자용" class="user">사용자 회원가입</button>
					<button type="button" onClick="location.href='./ownersignup.html'"
						class="owner">사업자 회원가입</button>
				</div>

			</div>
		</div>
	</div>
</body>
</html>