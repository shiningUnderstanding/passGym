//아이디(이메일) 중복확인 클릭
function idDupChk($idObj, $iddupchkBtObj){
    $iddupchkBtObj.click(function(){
        if($idObj.val().trim() == ''){
            alert('아이디를 입력하세요');
            $idObj.focus();
            return false;
        }

        let ajaxUrl = "./useriddupchk";
        let ajaxMethod = "get";
        let idValue = $idObj.val().trim();
        $.ajax({
            url: ajaxUrl,
            method: ajaxMethod,
            data: {id:idValue},
            success: function(responseObj){
                if(responseObj.status == 0){
                    alert('이미 사용중인 이메일입니다');
                } else if(responseObj.status == 1) {
                    alert('사용가능한 이메일입니다');
                }
            }
        });
    });
}

//핸드폰번호 중복확인 클릭
function phoneNoDupChk($phonenoObj, $phonenodupchkBtObj){
    $phonenodupchkBtObj.click(function(){
        if($phonenoObj.val().trim() == ''){
            alert('번호를 입력하세요');
            $phonenoObj.focus();
            return false;
        }
        
        let ajaxUrl = "./phonenumdupchk";
        let ajaxMethod = 'post';
        let phonenoValue = $phonenoObj.val().trim();
        alert("전달된 전화번호: "+phonenoValue);
        $.ajax({
            url: ajaxUrl,
            method: ajaxMethod,
            data: {phone_no: phonenoValue},
            success:function(responseObj){
                if(responseObj.status == 0){
                    alert('해당 번호로 가입된 사용자가 있습니다');
                }else if(responseObj.status == 1){
                    alert('인증번호가 전송되었습니다');
                }
            }
        });
    });
}

// function searchZip(){
    
// }

//폼전송
function userSignupClick($userSignupFormObj){
    $userSignupFormObj.submit(function(){
        //비밀번호값 유효성검사
        let $pwdObjArr = $('.usersingup_pwd');
        let $pwd1 = $($pwdObjArr[0]);
        let $pwd2 = $($pwdObjArr[1]);
        console.log($pwd1.val());
        console.log($pwd2.val());

        if($pwd1.val() != $pwd2.val()){
            alert('비밀번호가 일치하지 않습니다');
            $pwd1.focus();
            return false;
        }

        let ajaxUrl = $(this).attr("action");
        let ajaxMethod = $(this).attr("method");
        let sendData = $(this).serialize();
        alert("url:" + ajaxUrl);

        $.ajax({
            url: ajaxUrl,
            method: ajaxMethod,
            data: sendData,
            success: function(responseObj){
                console.log(responseObj);
                alert(responseObj.msg);
                if(responseObj.status == 1){
					alert("회원가입이 완료되었습니다.");
                    location.href='./';
                }
            }, error: function(xhr){
                alert("응답실패:" + xhr.status);
            }
        });
        return false;
    });
}