<%@page import="com.passgym.user.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%User user = (User)request.getAttribute("user"); %>
<link rel="stylesheet" href="./css/edituser.css" />
<script>
//-----------------프로필 이미지 미리보기 시작--------------
$('#profileImgInput').change(function(){
    setImageFromFile(this, '#profileImg');
});

function setImageFromFile(input, expression) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $(expression).attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}
//-----------------프로필 이미지 미리보기 끝--------------

//----------------수정-저장버튼 토글 시작-----------------
$(".canEditBtn").click(function(){
	
	return false;
});
//----------------수정-저장버튼 토글  끝-----------------
</script>
<!-- 카카오 우편번호 검색 api -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
            function searchZip(){
                new daum.Postcode({
                    oncomplete: function(data) {
                        var addr = ''; //도로명 주소 변수
                        if(data.userSelectedType == 'R'){ //사용자가 도로명 주소를 선택했을 경우
                            addr = data.roadAddress;
                        } 
                        else { //사용자가 지번 주소를 선택했을 경우
                            addr = data.jibunAddress;
                        }

                        // 우편번호와 주소 정보를 해당 필드에 넣는다.
                        document.getElementById('zipcode').value = data.zonecode;
                        document.getElementById("addr").value = addr;
                        // 커서를 상세주소 필드로 이동한다.
                        document.getElementById("addrDetail").focus();
                    }
                }).open();
                return false;
            }
        </script>
<div class="edituser">
	<div class="edituser__title">사용자정보 수정</div>
	<form class="edituser__form" action="edit" method="post">
		<div class="edituser__img">
			<div class="editprofile__img">
				<img id="profileImg" src="./images/user/<%=user.getUserNo() %>.jpg" />
			</div>
			<input id="profileImgInput" type="file" name="photo" />
		</div>
		<div class="edituser__info">
			<div class="edituser__infoContainer">
				<div class="edituser__id edituser__infoline">
					<span>아이디 : </span><%=user.getId() %>
				</div>
				<div class="edituser__name edituser__infoline">
					<span>이름 :</span> <input type="text" name="name"
						placeholder="바꿀 이름을 입력하세요" value="<%=user.getName() %>" required
						readonly />
					<button class="canEditBtn">수정</button>
				</div>
				<div class="edituser__pwd edituser__infoline">
					<span>비밀번호 :</span> <input type="password" name="pwd"
						placeholder="비밀번호" value="<%=user.getPwd() %>" required readonly />
					<button class="canEditBtn">수정</button>
				</div>
				<div class="edituser__phoneNo edituser__infoline">
					<span>휴대폰번호 :</span> <input type="text" name="phoneNo"
						placeholder="휴대폰번호" value="01062329789" required readonly />
					<button class="canEditBtn">수정</button>
				</div>
				<div class="edituser__zipcode edituser__infoline">
					<span>우편번호 :</span> <input id="zipcode" type="text" name="zipcode"
						placeholder="우편번호" value="<%=user.getZipcode() %>" size="5"
						required readonly />
					<input type="button" class="searchzipBtn" onclick="searchZip()" id="searchzip" value="주소 찾기"><br>
				</div>
				<div class="edituser__addr edituser__infoline">
					<span>주소 :</span> <input id="addr" type="text" name="addr" placeholder="주소"
						value="<%=user.getAddr() %>" size="36" required readonly />
				</div>
				<div class="edituser__addrDetail edituser__infoline">
					<span>상세주소 :</span> <input id="addrDetail" type="text" name="addrDetail"
						placeholder="상세주소" value="<%=user.getAddrDetail() %>" required
						readonly />
					<button class="canEditBtn">수정</button>
				</div>
			</div>
		</div>
		<div class="edituser__saveBtn">
			<button class="saveBtn">저장</button>
		</div>
		<div class="edituser__withdrawalBtn">
			<button class="withdrawalBtn">회원탈퇴</button>
		</div>
	</form>
</div>