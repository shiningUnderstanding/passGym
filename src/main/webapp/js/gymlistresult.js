/**
 * 헬스장 상세보기 클릭
 */
function gymDetail() {
	let $divObj = $('.gymlist__detail');
	$divObj.click(function() {
		let ownerNo = $(this).attr('id');
		let ajaxUrl = "./gymdetail";
		let ajaxMethod = "get";
		$.ajax({
			url: ajaxUrl,
			method: ajaxMethod,
			data: { ownerNo: ownerNo },
			success: function(responseData) {
				location.href="./gymdetail";
			}

		});
		return false;
	});
}