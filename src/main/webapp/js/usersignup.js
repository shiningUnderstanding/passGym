function idDupChk($idObj, $iddupchkBtObj){
    $iddupchkBtObj.click(function(){
        //아이디 입력값 유효성 검사
        if($idObj.val().trim() == ''){
            alert('아이디를 입력하세요');
            $idObj.focus();
            return false;
        }

        let ajaxUrl = "./useriddupchk";
        let ajaxMethod = "get";
        let idValue = $idObj.val().trim();
        $ajax({
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

function searchZip(){
    
}


function userSignupClick(){
    let $userSignupFormObj = $('form#usersignupform');
    

    $userSignupFormObj.submit(function(){
        //비밀번호값 유효성검사
        let $pwdObjArr = $('usersignup__container>:password');
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
        alert("전송데이터:" + sendData);

        $ajax({
            url: ajaxUrl,
            method: ajaxMethod,
            data: sendData,
            success: function(responseObj){
                alert(responseObj.msg);
                if(responseObj.status == 1){
                    location.href='./';
                }
            }, error: function(xhr){
                alert("응답실패:" + xhr.status);
            }
        });
        return false;
    });
}