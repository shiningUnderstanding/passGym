<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>passGym : 로그인</title>
        <meta charset="UTF-8">
        <style>
          
            .login{
            	width:100%;
            	heigt: 100vh;
            }
            
            .login__header{
                width: 100px;
                text-align: center;
            }
            .login__mainLogo{
                width: 100%;
            }
            
            .login__hr{
                display: flex;
                flex-basis: 100%;
                align-content: center;
                color: rgba(0, 0, 0, 0.35);
                font-size: 12px;
                margin: 8px 0px;
            }
            .login__hr::before,
            .login__hr::after{
                content: "";
                flex-grow: 1;
                background-color: rgba(0, 0, 0, 0.35);
                height: 1px;
                font-size: 0px;
                line-height: 0px;
                margin: 0px 16px;
            }
            .login__sns{
                width: 50px;
                /* display: flex;
                justify-content: space-around; */
                margin: 10px;
                border-radius: 50%;
            }
            .signin{
                display: flex;
                justify-content: center;
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="./js/login.js"></script>
        <script>
            $(function(){
            	loginClick();
            	//body > section > div > div > div > div.signin > button.user
            	$('div.login__body div.signin>button.user').click(function(){
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
                            <label><input type="radio" class="login__userChk" name="check" id="userChk" value="user">사용자</label>
                            <label><input type="radio" class="login__ownerChk" name="check" id="ownerChk" value="owner">사업자</label><br>
                        </div>
                        <div class="login__input">
                            <input type="email" class="login__id" name="id" required placeholder="이메일"><br>
                            <input type="password" class="login__pwd" name="pwd" required placeholder="비밀번호"><br>
                            <button type="submit" class="login__submit" value="로그인">로그인</button><br>
                        </div>
                    </form>

                    <div class="login__find">
                        <!-- <button type="button" value="이메일/비밀번호 찾기">이메일/비밀번호 찾기</button> -->
                        <a href="./findemail.html">이메일 찾기</a>
                        <a href="./findpassword.html">비밀번호 찾기</a>
                    </div>
                    
                    <div class="login__hr">간편로그인</div>
                        <div class="login__sns">
                            <a href="">
                            <img src="./images/kakao.png" class="login__sns" alt="카카오톡으로 로그인"></a>
                            <a href="">
                            <img src="./images/naver.png" class="login__sns" alt="네이버로 로그인"></a>
                        </div>
                    <div class="login__hr">회원가입</div>
                        <div class="signin">
                           <!--  <button type="button" value="사용자용" onclick="location.href='./usersignup.jsp'">사용자 회원가입</button> -->
                            <button type="button" value="사용자용" class="user">사용자 회원가입</button>
                            <button type="button" onClick="location.href='./ownersignup.html'">법인 회원가입</button>
                        </div>

                </div>
            </div>
        </div>
    </body>
</html>