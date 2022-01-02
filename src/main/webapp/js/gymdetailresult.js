/**
 *옵션 선택 클릭
 */
/**
 * 결제 클릭
 */
function paymentClick() {
	let $divObj = $('.gymList__payment');
	$divObj.click(function() {
		let data = $(this).serialize();
		let ajaxUrl = "./paymentpass";
		let method = "post";
		$.ajax({
			url: ajaxUrl,
			method: method,
			data: data,
			success: function() {
				$()
			}

		});
	});
}