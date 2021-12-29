/**
 * 헬스장 상세보기 클릭
 */
function gymDetail(){
    let $divObj = $('.gymList__detail');
    $divObj.click(function(){
        let ownerNo = $(this).parent().attr('id');
        let ajaxUrl = "./gymdetail"
        $.ajax({
            url: ajaxUrl,
            

        })
    });
}