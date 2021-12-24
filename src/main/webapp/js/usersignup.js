function userSignupClick(){
    let $userSignupFormObj = $('form#usersignupform');
    

    $userSignupFormObj.submit(function(){
        let ajaxUrl = $(this).attr("action");
        let ajaxMethod = $(this).attr("method");
        let idValue = $(this)
    })
}