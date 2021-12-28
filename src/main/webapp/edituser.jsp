<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="./css/edituser.css" />
    <div class="edituser">
      <div class="edituser__title">사용자정보 수정</div>
      <form class="edituser__form" method="post" action="./edituser">
        <div class="profile__img">
          <img src="./images/user/2.jpg" />
        </div>
        <input type="file" name="photo" />
        <div class="edituser__info">
          <div class="edituser__id"><span>아이디 : </span>id1@naver.com</div>
          <div class="edituser__name">
            <span>이름 :</span>
            <input
              type="text"
              name="이름"
              placeholder="바꿀 이름을 입력하세요"
              value="김건우"
              required
              readonly
            />
            <button>수정</button>
          </div>
          <div class="edituser__pwd">
            <span>비밀번호 :</span>
            <input
              type="password"
              name="비밀번호"
              placeholder="현재 비밀번호"
              value="123456"
              required
              readonly
            />
            <button>수정</button>
          </div>
          <div class="edituser__phoneNo">
            <span>휴대폰번호 :</span>
            <input
              type="text"
              name="비밀번호"
              placeholder="현재 비밀번호"
              value="01062329789"
              required
              readonly
            />
            <button>수정</button>
          </div>
          <div class="edituser__zipcode">
            <span>우편번호 :</span>
            <input
              type="text"
              name="비밀번호"
              placeholder="현재 비밀번호"
              value="12345"
              required
              readonly
            />
            <button>수정</button>
          </div>
          <div class="edituser__addr">
            <span>주소 :</span>
            <input
              type="text"
              name="비밀번호"
              placeholder="현재 비밀번호"
              value="경기도 수원시 지동 270번지"
              required
              readonly
            />
            <button>수정</button>
          </div>
          <div class="edituser__addrDetail">
            <span>상세주소 :</span>
            <input
              type="text"
              name="비밀번호"
              placeholder="현재 비밀번호"
              value="포레스트 311호"
              required
              readonly
            />
            <button>수정</button>
          </div>
        </div>
        <div><button>저장</button><button>회원탈퇴</button></div>
      </form>
    </div>