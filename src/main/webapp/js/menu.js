/**
 * 
 */

function menuClick(){
    let $menuObj = $(".link");
    $menuObj.click(function() {
        let menuHref = $(this).attr("href");
        let ajaxUrl = "";
        let ajaxMethod = "";
        switch (menuHref) {
        case "login":
            ajaxUrl = menuHref;
            ajaxMethod = "get";

        case "logout":
            ajaxUrl = menuHref;
            $.ajax({
                url: ajaxUrl,
                success: function(){
                    location.href="./index.jsp";
                },
                error: function(xhr){
                    alert('응답실패: ' + xhr.status);
                }
            });
            return false;
              
        case "mypage":
            //AJAX요청, 응답
            ajaxUrl = menuHref;
            ajaxMethod = "post";
            let userNo = 2;
            $("section").empty();
            //( String responseText, String textStatus, jqXHR jqXHR)
            $.ajax({
                url : ajaxUrl,
                method : ajaxMethod,
                data : {
                    userNo : userNo
                }, 
                success : function(responseData) {
                    $("section").html(responseData);
                },
                error : function(xhr) {
                    alert("응답실패 status : " + xhr.status);
                },
            });
            return false;
        }
    });
}