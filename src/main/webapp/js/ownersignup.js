function formSubmitted(){
    let $formObj = $(".ownersignup__form");
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
        let sendData = $('ownersignup__form').serialize();
        alert("전송데이터:" + sendData);

        $.ajax({
            url: ajaxUrl,
            method: ajaxMethod,
            data:sendData,
            success:function(responseData){
                let resultNum = parseInt(responseData.trim());
                alert(responseData);
                if(resultNum == 0){
                    alert("가입실패");
                }else if(resultNum == 1){
                    alert("가입성공");
                    location.href="./login.html";
                }
                }, error:function(xhr){
                    alert("응답실패:" + xhr.status);
                }
        });
        return false;
    });
}