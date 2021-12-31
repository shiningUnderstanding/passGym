function loginClick(){
    let $loginFormObj = $('form#loginform');
    let $userLoginObj = $('input#userChk');
    let $ownerLoginObj = $('input#ownerChk');

    

    $loginFormObj.submit(function(){
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
        
        let ajaxUrl = $(this).attr("action");
        let ajaxMethod = $(this).attr('method');
        let idValue = $(this).find('input[name=id]').val();
        let pwdValue = $(this).find('input[name=pwd]').val();
        
        

        $.ajax({
            url: ajaxUrl,
            method: ajaxMethod,
            data: {id:idValue, pwd:pwdValue},
            success: function(responseObj){
                if(responseObj.status == 0){
                    alert("로그인 실패");
                }else if(responseObj.status == 1){
                    location.href = "./index.jsp";
                }  
                console.log(responseObj);
                console.log(responseObj.status);
            },
            error: function(xhr){
                alert("응답실패 status: " +  xhr.status);
            }
        });
        return false;
    })
}