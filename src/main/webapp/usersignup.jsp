<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>passGym : 사용자 회원가입</title>
        <meta charset="UTF-8">
        <style>
            body {
  				font-family: "Noto Sans kr", sans-serif;
			}
            
            /* .usersignup__header{
                width: 100px;
                text-align: center;
            }
            .usersignup__mainLogo{
                width: 100%;
            } */
            
			.usersignup__container{
				width: 312px;
				height: 50vh 100%;
			}

			.usersingup_pwd,
			.usersignup__name,
			.usersignup__addr1,
			.usersignup__addr2{
				display: flex;
                flex-direction: center;
                width: 100%!important;
                height: 48px;
    			margin-top: 0!important;
                border: 1px solid darkslategray;
            }

           .usersingup__id,
            .usersignup__phoneno,
            .usersignup__zipcode{
                display: flex;
                flex-direction: center;
                width: 70%!important;
                height: 48px;
    			margin-top: 0!important;
                border: 1px solid darkslategray;
            }

            .id__line,
            .phone__line,
            .zip__line{
            	width: 100%;
                display: flex;
                align-content: center;
                margin-bottom: 10%;
            }
            .usersignup__submit{
            	width: 100%;
			    height: 48px;
			    background-color: #f6f5ef;
                border: 1px solid #f6f5ef;
                border-radius: 4px;
                font-size: 16px;
                color: darkslategray;
			    cursor: pointer;
			}

            .usersignup__submit:hover{
                width: 100%;
                height: 48px;
                background-color: #dad9d6;
                border: 1px solid #dad9d6;
                border-radius: 4px;
                font-size: 16px;
                color: darkslategray;
                cursor: pointer;
            }
            
            .usersignup__hr {
                display: flex;
                justify-content: center;
                color: darkslategray;
            }

            .usersignup__sns{
                display: flex;
                justify-content: center;
            }

            .usersignup__kakao, .usersignup__naver {
                margin: 10px;
                width: 50px;
                display: flex;
                justify-content: center;
                border-radius: 50%;
            }
            
            .button{
                width: 30%;
			    height: 48px;
			    background-color: #f6f5ef;
                border: 1px solid #f6f5ef;
                border-radius: 4px;
                font-size: 12px;
                color: darkslategray;
			    cursor: pointer;
            }
            
            .button:hover{
                width: 30%;
			    height: 48px;
			    background-color: #dad9d6;
                border: 1px solid #dad9d6;
                border-radius: 4px;
                font-size: 12px;
                color: darkslategray;
			    cursor: pointer;
            }
        </style>
        <script src="./js/usersignup.js"></script>
        <script>
            $(function(){
                let $idObj = $('.usersingup__id');
                let $iddupchkBtObj = $('.usersignup__iddupchk');
                let $phonenoObj = $('.usersignup__phoneno');
                let $phonenodupchkBtObj = $('.usersignup__phonenochk');
                let $userSignupFormObj = $('.usersignup__form');
                

                //아이디(이메일) 중복확인 클릭
                idDupChk($idObj, $iddupchkBtObj);

                //핸드폰번호 중복확인 클릭
                phoneNoDupChk($phonenoObj, $phonenodupchkBtObj);

                //폼전송
            	userSignupClick($userSignupFormObj);
            });
            
        </script>

        <!-- 카카오 우편번호 검색 api -->
        <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
        <script>
            function searchZip(){
                new daum.Postcode({
                    oncomplete: function(data) {
                        var addr = ''; //도로명 주소 변수
                        if(data.userSelectedType == 'R'){ //사용자가 도로명 주소를 선택했을 경우
                            addr = data.roadAddress;
                        } 
                        else { //사용자가 지번 주소를 선택했을 경우
                            addr = data.jibunAddress;
                        }

                        // 우편번호와 주소 정보를 해당 필드에 넣는다.
                        document.getElementById('zipcode').value = data.zonecode;
                        document.getElementById("addr1").value = addr;
                        // 커서를 상세주소 필드로 이동한다.
                        document.getElementById("addr2").focus();
                    }
                }).open();
            }
        </script>
    </head>

    <body>
        <div class="usersignup">
            <!-- <div class="usersignup__header">
                <header>
                    <h1 class="usersignup__main">
                        로고이미지
                        <a href="index.html">
                        <span>패스짐</span>
                        <img class="usersignup__mainLogo" src="./images/logo.png"/>
                        </a>
                    </h1>
                </header>
            </div> -->
            <div class="usersignup__container">
                <form class="usersignup__form" id="usersignupform" method="post" action="./usersignup">
                    <div class="id__line">
                    <input type="email" class="usersingup__id input" name="id" placeholder="아이디(이메일)">
                    <button type="button" class="usersignup__iddupchk button">중복확인</button><br>
                    </div>
                    <input type="password" class="usersingup_pwd input" name="pwd" placeholder="비밀번호"><br>
                    <input type="password" class="usersingup_pwd input" name="pwd1" placeholder="비밀번호 확인"><br>
                    <input type="text" class="usersignup__name input" id="name" name="name" placeholder="이름"><br>
                    <div class="phone__line"><input type="text" class="usersignup__phoneno input" id="phone_no" name="phone_no" placeholder="핸드폰번호">
                    <button type="button" class="usersignup__phonenochk button">중복확인</button><br>
                    </div>
                    <!-- <input type="text" placeholder="인증번호 입력"> -->
                    <div class="zip__line"><input type="text" class="usersignup__zipcode input" id="zipcode" name="zipcode" onclick="searchZip()" readonly size="5" placeholder="우편번호">
                    <input type="button" class="usersignup__searchzip button" id="searchzip" onclick="searchZip()" value="우편번호 찾기"><br></div>
                    <input type="text" class="usersignup__addr1 input" name="addr1" id="addr1" onclick="searchZip()" readonly size="50" placeholder="주소"><br>
                    <input type="text" class="usersignup__addr2 input" name="addr2" id="addr2" placeholder="상세주소 입력"><br>
                    <button type="submit" class="usersignup__submit">회원가입</button>
                </form>
            </div>
            <br>
            <div class="usersignup__hr">간편회원가입</div>
            <br>
            <div class="usersignup__sns">
                <a href="./" >
                <img src="./images/kakao.png" class="usersignup__kakao" alt="카카오톡으로 가입"></a>
                <a href="./">
                <img src="./images/naver.png" class="usersignup__naver" alt="네이버로 가입"></a>
            </div>
        </div>
    </body>