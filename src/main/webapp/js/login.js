function loginClick(){
    let $loginFormObj = $('div.login>form');
    let $userLoginObj = $('input#userChk');
    let $ownerLoginObj = $('input#ownerChk');

    

    $loginFormObj.submit(function(){
        let ajaxUrl = $(this).attr("action");
        let ajaxMethod = $(this).attr('method');
        let idValue = $(this).find('input[name=id]').val();
        let pwdValue = $(this).find('input[name=pwd]').val();

        
        if($userLoginObj.prop('checked') == true){
            $("#loginform").attr("action", "./userlogin");   
            console.log("user choose");
        }else if($ownerLoginObj.prop('checked') == true){
            $("#loginform").attr("action", "./ownerlogin");   
            console.log("owner choose");
        } else {
            alert("체크박스를 선택하세요.");
            return false;
        };

        $.ajax({
            url: ajaxUrl,
            method: ajaxMethod,
            data: {id:idValue, pwd:pwdValue},
            success: function(responseData){
                let resultNum = parseInt(responseData.trim());
                if(resultNum == 0){
                    alert("로그인 실패");
                }else if(resultNum == 1){
                    location.href = "./";
                }  
            },
            error: function(xhr){
                alert("응답실패 status: " +  xhr.status);
            }
        });
        return false;
    })
}