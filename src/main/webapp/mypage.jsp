<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>maypage.jsp</title>
<link rel="stylesheet" href="./css/header.css" />
<link rel="stylesheet" href="./css/mypage.css" />
<link rel="stylesheet" href="./css/footer.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function() {
		//----------------------------------
		let ajaxUrl = "./mypage";
		let ajaxMethod = "get";
		let userNoValue = 1;
		$.ajax({
			url : ajaxUrl,
			method : ajaxMethod,
			data : {
				userNo : userNoValue
			},
			success : function(responseData) {
				alert("응답성공 : " + responseData);
				$("section>div.articles").html(responseData);
			},
			error : function(xhr) {
				alert("응답실패 status : " + xhr.status);
			},
		});
		//-----------------------------------
	});
</script>
</head>
<body>
	<header>
		<jsp:include page="./menu.jsp" />
	</header>

	<section class="section">
		<div class="profile_title">my profile</div>
		<div class="profile">
			<div class="profile__img">
				<img src="./images/Raccoons.jpg" />
			</div>
			<div class="profile__info">
				<span>이름 : 이현규</span> <span>이메일 : dlgusrb1913@naver.com</span> <span>주소
					: 경기도 수원시 팔달구 지동 276 (15647) 포레스트 311호</span>
			</div>
			<div class="profile__edit">
				<button class="profile__editBtn">수정</button>
			</div>
		</div>

		<div class="gympass__title">결제한 헬스장 정보</div>
		<div class="gympass">
			<div class="gympass__list">
				<div class="gympass__detail">
					<div class="gympass__infos">
						<div class="gympass__info-top">
							<div class="gympass__no">no.21654215</div>
							<div class="gympass__status">사용중</div>
						</div>
						<div class="gympass__info-mid">
							<div class="gympass__gym">
								<div class="gympass__gymName">myGym헬스장</div>
								<div class="gympass__passName">1개월권</div>
							</div>
							<div class="star">★☆☆☆☆ 별점을 주세요</div>
						</div>
						<div class="gympass__info-bot">
							<div class="gympass__date">2021년 12월 22일 ~ 2022년 3월 21일</div>
							<div class="remain">남은 일수 : 10일</div>
						</div>
					</div>
					<div class="gympass__btns">
						<button class="gympass__btn">연장</button>
						<button class="gympass__btn">일시정지</button>
					</div>
				</div>
				<div class="gympass__detail">
					<div class="gympass__infos">
						<div class="gympass__info-top">
							<div class="gympass__no">no.21654215</div>
							<div class="gympass__status">사용중</div>
						</div>
						<div class="gympass__info-mid">
							<div class="gympass__gym">
								<div class="gympass__gymName">myGym헬스장</div>
								<div class="gympass__passName">1개월권</div>
							</div>
							<div class="star">★☆☆☆☆ 별점을 주세요</div>
						</div>
						<div class="gympass__info-bot">
							<div class="gympass__date">2021년 12월 22일 ~ 2022년 3월 21일</div>
							<div class="remain">남은 일수 : 10일</div>
						</div>
					</div>
					<div class="gympass__btns">
						<button class="gympass__btn">연장</button>
						<button class="gympass__btn">일시정지</button>
					</div>
				</div>
				<div class="gympass__detail">
					<div class="gympass__infos">
						<div class="gympass__info-top">
							<div class="gympass__no">no.21654215</div>
							<div class="gympass__status">사용중</div>
						</div>
						<div class="gympass__info-mid">
							<div class="gympass__gym">
								<div class="gympass__gymName">myGym헬스장</div>
								<div class="gympass__passName">1개월권</div>
							</div>
							<div class="star">★☆☆☆☆ 별점을 주세요</div>
						</div>
						<div class="gympass__info-bot">
							<div class="gympass__date">2021년 12월 22일 ~ 2022년 3월 21일</div>
							<div class="remain">남은 일수 : 10일</div>
						</div>
					</div>
					<div class="gympass__btns">
						<button class="gympass__btn">연장</button>
						<button class="gympass__btn">일시정지</button>
					</div>
				</div>
				<div class="gympass__detail">
					<div class="gympass__infos">
						<div class="gympass__info-top">
							<div class="gympass__no">no.21654215</div>
							<div class="gympass__status">사용중</div>
						</div>
						<div class="gympass__info-mid">
							<div class="gympass__gym">
								<div class="gympass__gymName">myGym헬스장</div>
								<div class="gympass__passName">1개월권</div>
							</div>
							<div class="star">★☆☆☆☆ 별점을 주세요</div>
						</div>
						<div class="gympass__info-bot">
							<div class="gympass__date">2021년 12월 22일 ~ 2022년 3월 21일</div>
							<div class="remain">남은 일수 : 10일</div>
						</div>
					</div>
					<div class="gympass__btns">
						<button class="gympass__btn">연장</button>
						<button class="gympass__btn">일시정지</button>
					</div>
				</div>
				<div class="gympass__detail">
					<div class="gympass__infos">
						<div class="gympass__info-top">
							<div class="gympass__no">no.21654215</div>
							<div class="gympass__status">사용중</div>
						</div>
						<div class="gympass__info-mid">
							<div class="gympass__gym">
								<div class="gympass__gymName">myGym헬스장</div>
								<div class="gympass__passName">1개월권</div>
							</div>
							<div class="star">★☆☆☆☆ 별점을 주세요</div>
						</div>
						<div class="gympass__info-bot">
							<div class="gympass__date">2021년 12월 22일 ~ 2022년 3월 21일</div>
							<div class="remain">남은 일수 : 10일</div>
						</div>
					</div>
					<div class="gympass__btns">
						<button class="gympass__btn">연장</button>
						<button class="gympass__btn">일시정지</button>
					</div>
				</div>
				<div class="gympass__detail">
					<div class="gympass__infos">
						<div class="gympass__info-top">
							<div class="gympass__no">no.21654215</div>
							<div class="gympass__status">사용중</div>
						</div>
						<div class="gympass__info-mid">
							<div class="gympass__gym">
								<div class="gympass__gymName">myGym헬스장</div>
								<div class="gympass__passName">1개월권</div>
							</div>
							<div class="star">★☆☆☆☆ 별점을 주세요</div>
						</div>
						<div class="gympass__info-bot">
							<div class="gympass__date">2021년 12월 22일 ~ 2022년 3월 21일</div>
							<div class="remain">남은 일수 : 10일</div>
						</div>
					</div>
					<div class="gympass__btns">
						<button class="gympass__btn">연장</button>
						<button class="gympass__btn">일시정지</button>
					</div>
				</div>
				<div class="gympass__detail">
					<div class="gympass__infos">
						<div class="gympass__info-top">
							<div class="gympass__no">no.21654215</div>
							<div class="gympass__status">사용중</div>
						</div>
						<div class="gympass__info-mid">
							<div class="gympass__gym">
								<div class="gympass__gymName">myGym헬스장</div>
								<div class="gympass__passName">1개월권</div>
							</div>
							<div class="star">★☆☆☆☆ 별점을 주세요</div>
						</div>
						<div class="gympass__info-bot">
							<div class="gympass__date">2021년 12월 22일 ~ 2022년 3월 21일</div>
							<div class="remain">남은 일수 : 10일</div>
						</div>
					</div>
					<div class="gympass__btns">
						<button class="gympass__btn">연장</button>
						<button class="gympass__btn">일시정지</button>
					</div>
				</div>
			</div>
		</div>
		<div class="zzim__title">찜한 헬스장 정보</div>
		<div class="zzim">
			<div class="zzim__list">
				<div class="zzim__detail">
					<div class="zzim__gymImg">
						<img src="./images/Raccoons.jpg" />
					</div>
					<div class="zzim__infos">
						<div class="zzim__info-top">
							<div class="zzim__gymName">배달의GYM</div>
							<div class="zzim__star">★4.8</div>
						</div>
						<div class="zzim__gymPhone">031-279-1545</div>
						<div class="zzim__gymAddr">경기도 수원시 팔달구 우만동 270번지(15478)</div>
						<div class="zzim__gymAddrDetail">유성빌라 3층</div>
					</div>
					<div class="zzim__btns">
						<button class="zzim__deleteBtn">삭제</button>
					</div>
				</div>
				<div class="zzim__detail">
					<div class="zzim__gymImg">
						<img src="./images/Raccoons.jpg" />
					</div>
					<div class="zzim__infos">
						<div class="zzim__info-top">
							<div class="zzim__gymName">배달의GYM</div>
							<div class="zzim__star">★4.8</div>
						</div>
						<div class="zzim__gymPhone">031-279-1545</div>
						<div class="zzim__gymAddr">경기도 수원시 팔달구 우만동 270번지(15478)</div>
						<div class="zzim__gymAddrDetail">유성빌라 3층</div>
					</div>
					<div class="zzim__btns">
						<button class="zzim__deleteBtn">삭제</button>
					</div>
				</div>
				<div class="zzim__detail">
					<div class="zzim__gymImg">
						<img src="./images/Raccoons.jpg" />
					</div>
					<div class="zzim__infos">
						<div class="zzim__info-top">
							<div class="zzim__gymName">배달의GYM</div>
							<div class="zzim__star">★4.8</div>
						</div>
						<div class="zzim__gymPhone">031-279-1545</div>
						<div class="zzim__gymAddr">경기도 수원시 팔달구 우만동 270번지(15478)</div>
						<div class="zzim__gymAddrDetail">유성빌라 3층</div>
					</div>
					<div class="zzim__btns">
						<button class="zzim__deleteBtn">삭제</button>
					</div>
				</div>
				<div class="zzim__detail">
					<div class="zzim__gymImg">
						<img src="./images/Raccoons.jpg" />
					</div>
					<div class="zzim__infos">
						<div class="zzim__info-top">
							<div class="zzim__gymName">배달의GYM</div>
							<div class="zzim__star">★4.8</div>
						</div>
						<div class="zzim__gymPhone">031-279-1545</div>
						<div class="zzim__gymAddr">경기도 수원시 팔달구 우만동 270번지(15478)</div>
						<div class="zzim__gymAddrDetail">유성빌라 3층</div>
					</div>
					<div class="zzim__btns">
						<button class="zzim__deleteBtn">삭제</button>
					</div>
				</div>
				<div class="zzim__detail">
					<div class="zzim__gymImg">
						<img src="./images/Raccoons.jpg" />
					</div>
					<div class="zzim__infos">
						<div class="zzim__info-top">
							<div class="zzim__gymName">배달의GYM</div>
							<div class="zzim__star">★4.8</div>
						</div>
						<div class="zzim__gymPhone">031-279-1545</div>
						<div class="zzim__gymAddr">경기도 수원시 팔달구 우만동 270번지(15478)</div>
						<div class="zzim__gymAddrDetail">유성빌라 3층</div>
					</div>
					<div class="zzim__btns">
						<button class="zzim__deleteBtn">삭제</button>
					</div>
				</div>
				<div class="zzim__detail">
					<div class="zzim__gymImg">
						<img src="./images/Raccoons.jpg" />
					</div>
					<div class="zzim__infos">
						<div class="zzim__info-top">
							<div class="zzim__gymName">배달의GYM</div>
							<div class="zzim__star">★4.8</div>
						</div>
						<div class="zzim__gymPhone">031-279-1545</div>
						<div class="zzim__gymAddr">경기도 수원시 팔달구 우만동 270번지(15478)</div>
						<div class="zzim__gymAddrDetail">유성빌라 3층</div>
					</div>
					<div class="zzim__btns">
						<button class="zzim__deleteBtn">삭제</button>
					</div>
				</div>
			</div>
		</div>
		<div class="qna__title">1 : 1 문의</div>
		<div class="qna">
			<div class="qna__list">
				<div class="qna__detail">
					<div class="qna_infos">
						<div class="qna__info-show">
							<div class="qna__detailTitle">
								<span>문의 제목 : </span>문의드립니다.
							</div>
							<div class="qna__date">
								<span>문의 날짜 : </span>2021/12/22
							</div>
							<div class="qna__replyStatus">
								<span>상태 : </span>답변대기
							</div>
							<div class="qna__contentBtn">
								<button class="contentBtn">내용보기</button>
							</div>
						</div>
						<div class="qna__info-hidden">
							<div class="qna_content">
								<span>문의 내용 : </span> 헬스장 환불관련해서 궁금한게 있는데 이걸 어디다 물어봐야하나요?
							</div>
							<div class="qna__reply">
								<span>답변 내용 : </span>아직 문의를 처리하지 못하였습니다.
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="qna__list">
				<div class="qna__detail">
					<div class="qna_infos">
						<div class="qna__info-show">
							<div class="qna__detailTitle">
								<span>문의 제목 : </span>문의드립니다.
							</div>
							<div class="qna__date">
								<span>문의 날짜 : </span>2021/12/22
							</div>
							<div class="qna__replyStatus">
								<span>상태 : </span>답변대기
							</div>
							<div class="qna__contentBtn">
								<button class="contentBtn">내용보기</button>
							</div>
						</div>
						<div class="qna__info-hidden">
							<div class="qna_content">
								<span>문의 내용 : </span> 헬스장 환불관련해서 궁금한게 있는데 이걸 어디다 물어봐야하나요?
							</div>
							<div class="qna__reply">
								<span>답변 내용 : </span>아직 문의를 처리하지 못하였습니다.
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="qna__list">
				<div class="qna__detail">
					<div class="qna_infos">
						<div class="qna__info-show">
							<div class="qna__detailTitle">
								<span>문의 제목 : </span>문의드립니다.
							</div>
							<div class="qna__date">
								<span>문의 날짜 : </span>2021/12/22
							</div>
							<div class="qna__replyStatus">
								<span>상태 : </span>답변대기
							</div>
							<div class="qna__contentBtn">
								<button class="contentBtn">내용보기</button>
							</div>
						</div>
						<div class="qna__info-hidden">
							<div class="qna_content">
								<span>문의 내용 : </span> 헬스장 환불관련해서 궁금한게 있는데 이걸 어디다 물어봐야하나요?
							</div>
							<div class="qna__reply">
								<span>답변 내용 : </span>아직 문의를 처리하지 못하였습니다.
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="qna__list">
				<div class="qna__detail">
					<div class="qna_infos">
						<div class="qna__info-show">
							<div class="qna__detailTitle">
								<span>문의 제목 : </span>문의드립니다.
							</div>
							<div class="qna__date">
								<span>문의 날짜 : </span>2021/12/22
							</div>
							<div class="qna__replyStatus">
								<span>상태 : </span>답변대기
							</div>
							<div class="qna__contentBtn">
								<button class="contentBtn">내용보기</button>
							</div>
						</div>
						<div class="qna__info-hidden">
							<div class="qna_content">
								<span>문의 내용 : </span> 헬스장 환불관련해서 궁금한게 있는데 이걸 어디다 물어봐야하나요?
							</div>
							<div class="qna__reply">
								<span>답변 내용 : </span>아직 문의를 처리하지 못하였습니다.
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="qna__list">
				<div class="qna__detail">
					<div class="qna_infos">
						<div class="qna__info-show">
							<div class="qna__detailTitle">
								<span>문의 제목 : </span>문의드립니다.
							</div>
							<div class="qna__date">
								<span>문의 날짜 : </span>2021/12/22
							</div>
							<div class="qna__replyStatus">
								<span>상태 : </span>답변대기
							</div>
							<div class="qna__contentBtn">
								<button class="contentBtn">내용보기</button>
							</div>
						</div>
						<div class="qna__info-hidden">
							<div class="qna_content">
								<span>문의 내용 : </span> 헬스장 환불관련해서 궁금한게 있는데 이걸 어디다 물어봐야하나요?
							</div>
							<div class="qna__reply">
								<span>답변 내용 : </span>아직 문의를 처리하지 못하였습니다.
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="qna__list">
				<div class="qna__detail">
					<div class="qna_infos">
						<div class="qna__info-show">
							<div class="qna__detailTitle">
								<span>문의 제목 : </span>문의드립니다.
							</div>
							<div class="qna__date">
								<span>문의 날짜 : </span>2021/12/22
							</div>
							<div class="qna__replyStatus">
								<span>상태 : </span>답변대기
							</div>
							<div class="qna__contentBtn">
								<button class="contentBtn">내용보기</button>
							</div>
						</div>
						<div class="qna__info-hidden">
							<div class="qna_content">
								<span>문의 내용 : </span> 헬스장 환불관련해서 궁금한게 있는데 이걸 어디다 물어봐야하나요?
							</div>
							<div class="qna__reply">
								<span>답변 내용 : </span>아직 문의를 처리하지 못하였습니다.
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="question">
				<button class="question__btn">문의하기</button>
			</div>
		</div>
	</section>
	<footer class="footer">
		<%@ include file="./footer.jsp"%>
	</footer>
</body>
</html>