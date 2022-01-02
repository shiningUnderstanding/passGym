function formSubmitted($formObj){
    $formObj.submit(function(){
        let $pwdObjArr = $('.ownersignup__form>:password');
        let $pwd1 = $($pwdObjArr[0]);
        let $pwd2 = $($pwdObjArr[1]);
        console.log($pwd1.val());
        console.log($pwd2.val());

        if($pwd1.val() != $pwd2.val()){
            alert('비밀번호가 일치하지 않습니다');
            $pwd1.focus();
            return false;
        }

        let ajaxUrl = $(this).attr('action');
        let ajaxMethod = $(this).attr('method');
        let sendData = $(this).serialize();

        $.ajax({
            url: ajaxUrl,
            method: ajaxMethod,
            data: sendData,
            success:function(responseObj){
                if(responseObj.status == 3){
                    alert("가입실패")
                } else if(responseObj.status == 2){
                    location.href = "./gymregist.jsp";
                }
                console.log(responseObj);
                }, error:function(xhr){
                    alert("응답실패:" + xhr.status);
                }
        });
        return false;
    });
}

function idDupChk($idObj, $submitBtObj){
    let $idDupChkBt = $(".ownersiginup__iddupchk");
    $idDupChkBt.click(function(){
        if($idObj.val().trim() == ""){
            alert("아이디를 입력하시오");
            $idObj.focus();
            return false;
        }
    let idValue = $idObj.val().trim();
        $.ajax({
            url: "./owneriddupchk",
            method: "post",
            data: {id : idValue},
            success: function(responseObj){
                if(responseObj.status == 0){
                    alert("이미 사용중인 아이디입니다.");
                }else if(responseObj.status == 1){
                    alert("사용가능한 아이디입니다.");
                    $submitBtObj.css('visibility','visible');
                }
            }
        });
    });
}

function idFocus($idObj, $submitBtObj){
    $idObj.focus(function(){
    $submitBtObj.css('visibility','hidden'); //'visible';
});
}