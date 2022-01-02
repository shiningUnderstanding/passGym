<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <meta charset="UTF-8">
    <html>
        <head>
            <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=442e73ef7496448fa1d785863f42076b&libraries=services"></script>
            <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script src="./js/ownersignup.js"></script>
            <script>  
            $(function(){
                let $formObj = $(".ownersignup__form");
                let $idObj = $(".ownersignup__idinput");
                let $submitBtObj = $(".ownersignup__signupbt");
                formSubmitted($formObj);
                idFocus($idObj, $submitBtObj);
                idDupChk($idObj, $submitBtObj);
            })
    
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
                            document.getElementById("addr").value = addr;
                            // 커서를 상세주소 필드로 이동한다.
                            document.getElementById("addrdetail").focus();
                            getLocation();
    
                        }
                    }).open();
                }
    
            function getLocation(){
                //kakao map API를 활용하여 위도 경도를 반환하는 함수
                var addr = document.getElementById('addr').value;
                //입력된 주소를 통해 좌표를 검색한다
                var geocoder = new kakao.maps.services.Geocoder();
                geocoder.addressSearch(addr, function(result, status) {
    
                    if (status === kakao.maps.services.Status.OK) {
                        let yLocation = result[0].y;
                        let xLocation = result[0].x; 
                        document.getElementById('lat').value = yLocation;
                        document.getElementById('lon').value = xLocation;
                    } 
                });
            }
            </script>
            
            <style>
                body {
	            font-family: "Noto Sans kr", sans-serif;
            }
            .ownersignup{
                width: 100%;
				height: 50vh 100%;
                display: flex;
                justify-content: center;
            }
            .ownersignup__idinput,
            .ownersignup__gymname,
            .ownersignup__gymphone,
            .ownersignup__registno,
            .ownersignup__pwd,
            .ownersignup__pwdchk,
            .ownersignup__zipcode,
            .ownersignup__searchaddr1,
            .ownersignup__searchaddr2{
                display: flex;
                flex-direction: center;
                width: 312px!important;
                height: 48px;
    			margin-top: 0!important;
                border: 1px solid darkslategray;
            }
            .ownersignup__signupbt{
                width: 312px;
			    height: 48px;
			    background-color: #f6f5ef;
                border: 1px solid #f6f5ef;
                border-radius: 4px;
                font-size: 16px;
                color: darkslategray;
			    cursor: pointer;
            }
    
            </style>
                 
        </head>
        <body>
        <div class="ownersignup">
            <form class="ownersignup__form" method="post" action="./ownersignup" autocomplete="off">
                <label>아이디</label>
                <input class="ownersignup__idinput" type="email" name="id" required>
                <button class="ownersiginup__iddupchk" type="button">중복확인</button>
                <br>
                <label>업체이름</label>
                <input type="text" class="ownersignup__gymname" name="name" required>
                <br>
                <label>업체전화번호</label>
                <input type="text" class="ownersignup__gymphone" name="phoneno" required>
                <br>
                <label>사업자등록번호</label>
                <input type="text" class="ownersignup__registno" name="registno" required>
                <button class="ownersignup__registchk" type="button">사업자인증</button>
                <br>
                <label>비밀번호</label>
                <input type="password" class="ownersignup__pwd" name="pwd" required>
                <br>
                <label>비밀번호확인</label>
                <input type="password" class="ownersignup__pwdchk" name="pwd2" required>
                <br>  
                주소 
                <input type="text" class="ownersignup__zipcode" name="zipcode" id="zipcode" readonly>
                <button type="button" class="ownersignup__zipcodebt" onclick="searchZip()">우편번호찾기</button>
                <br>
                <input type="text" class="ownersignup__searchaddr1" size="50" id="addr" name="addr" readonly><br>
                <input type="text" class="ownersignup__searchaddr2" size="50" id="addrdetail" name="addrdetail" >
                <input type="hidden" name="lat" id="lat">
                <input type="hidden" name="lon" id="lon">
                <br>
                <button type="submit" class="ownersignup__signupbt">가입</button>
            </form>
        </div>
        </body>
    </html>