function formSubmitted(){
    let $formObj = $(".regist__form");
    $formObj.submit(function(){
        let ajaxUrl = $(this).attr("action");
        let ajaxMethod = $(this).attr("method");
        let sendData = $(this).serialize();
        alert("전송데이터:" + sendData);

        $.ajax({
            url: ajaxUrl,
            method : ajaxMethod,
            data : sendData,
            sucess:function(responseData){
                //reponseData출력되지 않음
                alert(responseData);
                let resultNum = parseInt(responseData.trim());
                if(resultNum == 0){
                    alert("SQL문 오류");
                }else if(resultNum == 10){
                    alert("등록 실패");
                }else if(resultNum == 1){
                    alert("등록 성공");
                    location.href="./login.html";
                }
            }, error:function(xhr){
                alert("응답실패:" + xhr.status);
            }
        });
        return false;
    });
}