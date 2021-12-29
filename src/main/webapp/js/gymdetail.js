/**
 * 헬스장 상세 클릭
 */
function gymDetail(){
    let $divObj = $('.gymList__detail');
    $divObj.click(function(){
        let data = $(this).serialize();
        let ajaxUrl = "./gymdetail";
		let method = "get";
        $.ajax({
            url: ajaxUrl,
            method: method,
			data: data,
			success: function(){
				$()
			}

        });
    });
}