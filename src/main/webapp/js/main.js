/**
 * 
 */
/*
function GeoLocation(){
    // Geolocation API에 액세스할 수 있는지를 확인
    if (navigator.geolocation) {
        //위치 정보를 정기적으로 얻기
        var id = navigator.geolocation.watchPosition(
                function(pos) {
                    $('#latitude').html(pos.coords.latitude);     // 위도 
                    $('#longitude').html(pos.coords.longitude); // 경도 
                });
    } else {
        alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.")
    }
}
*//*
function getLocation() {
    let latitude, longitude;
    if (navigator.geolocation) { // GPS를 지원하면
        navigator.geolocation.getCurrentPosition(function(position) {
            latitude = position.coords.latitude;
            longitude = position.coords.longitude;
        }, function error (e) {
			alert("Geolocation 오류 "+e.code +": " + e.message);
        }, {
            enableHighAccuracy: false,
            maximumAge: 0,
            timeout: Infinity
        });
    } else {
        alert('GPS를 지원하지 않습니다');
        return;
    }
}
*/
function gymList() {
    let latitude, longitude;
    if (navigator.geolocation) { // GPS를 지원하면
        navigator.geolocation.getCurrentPosition(function(position) {
            latitude = position.coords.latitude;
            longitude = position.coords.longitude;
        }, function error (e) {
			alert("Geolocation 오류 "+e.code +": " + e.message);
        }, {
            enableHighAccuracy: false,
            maximumAge: 0,
            timeout: Infinity
        });
    } else {
        alert('GPS를 지원하지 않습니다');
        return;
    }
	let ajaxUrl = "./gymlist";
	let ajaxMethod = "get";
	$.ajax({
		url: ajaxUrl,
		method: ajaxMethod,
		//data: {latitude: latitude, longitude: longitude},
		success: function(responseData) {
			$("section").html(responseData);
		}
	});
}

