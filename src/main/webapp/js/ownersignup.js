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
        let sendData = $(this).serialize();
        alert("전송데이터:" + sendData);

        $.ajax({
            url: ajaxUrl,
            method: ajaxMethod,
            data:sendData,
            success:function(responseData){
                let resultNum = parseInt(responseData.trim()); 
                //2021/12/25 현재 가입성공이 뜨지 않음. resultNum에 값이 없는 것으로 판단 -> reponse를 Servlet에 추가해줌
                if(resultNum == 0){
                    alert("가입실패");
                }else if(resultNum == 1){
                    alert("가입성공");
                    location.href="./gymregist.jsp";
                }
                }, error:function(xhr){
                    alert("응답실패:" + xhr.status);
                }
        });
        return false;
    });
}