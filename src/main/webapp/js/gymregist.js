function formSubmitted(){
    let $formObj = $(".regist__form");
    $formObj.submit(function(){
        let ajaxUrl = $(this).attr("action");
        let ajaxMethod = $(this).attr("method");
        let iValue = $(this).find('input[name=i]').val(i);
        let sendData = $(this).serialize();
        alert("i:"+iValue);
        $.ajax({
            url: ajaxUrl,
            method : ajaxMethod,
            data : sendData,
            success:function(responseObj){
                if(responseObj.status == 0){
                    alert("헬스장 등록실패");
                } else if(responseObj.status == 1){
                    location.href = "./login.html";
                }
            }, error:function(xhr){
                alert("응답실패:" + xhr.status);
            }
        });
        return false;
    });
}

let i = 0;

function passAddBtClick(){
    let $passAddBt = $(".regist__passaddbt");
    $passAddBt.click(function(){
        i++;
        $( '<div class="regist__gympass'+i+'">'
        + '이용권 정보 입력<br>'
        + '이용권 번호 : <input class="regist__passno" type="text" name="passno'+i+'" value='+i+' readonly><br>'
        + '이용권 이름 : <input class="regist__passname" type="text" name="passname'+i+'"><br>'
        + '이용권 가격 : <input class="regist__passprice" type="text" name="passprice'+i+'"><br>'
        + '이용권 개월수 : <input class="regist__passmonth" type="text" name="passmonth'+i+'"><br>'
        + '일시정지 가능 횟수 : <input class="regist__pausecount" type="text" name="pausecount'+i+'"><br>'
        + '일시정지 가능 일수 : <input class="regist__pausedate" type="text" name="pausedate'+i+'"><br>'
        + '비고 : <input class="regist__remarks" type="text" name="remarks'+i+'"><br>'
        + '<br></div>'
        ).appendTo('.regist__gympassall');
    });

}

function passRemoveBtClick(){
    let $passRemoveBt = $(".regist__passremovebt");
    $passRemoveBt.click(function(){
        $('.regist__gympass'+i).remove();
        if(i == 0){
            return false;
        }
        i--;   
    });
    
}