<%@page import="com.passgym.gym.vo.Gym"%>
<%@page import="com.passgym.owner.vo.Owner"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>gymregist.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="./js/gymregist.js"></script>
<script>
    $(function(){
        formSubmitted();
    })
</script>
</head>
<meta charset="UTF-8">
<body>
<%
Owner sessionOwner = (Owner)session.getAttribute("signupInfo");
Gym sessionGym = (Gym)session.getAttribute("gymInfo");
%>
세션ID : <%=session.getId() %><br>
세션ownerNo : <%=sessionOwner.getOwnerNo() %><br>
세션gymName : <%=sessionGym.getName() %><br>
<div class="regist">
        <form class="regist__form" method="post" action="./gymregist">
            <div class="regist__header">
                <img class="regist__img" src=""><br>
                <div class="regist__gymname">
                헬스장 이름 : <%=sessionGym.getName()%>
                </div>
                <div class="regist__gymaddr">
                헬스장 주소<br> 
                <%=sessionGym.getAddr()%><br>
                <%=sessionGym.getAddrDetail()%><br>
                </div>
            </div>
            <input class="regist__gymintroduce" type="text" name="introduce"><br>
            <div class="regist__gympass" style="border: 1px solid #ccc">
                <input class="regist__passno" type="text" name="passno">
                <input class="regist__passname" type="text" name="passname">
                <input class="regist__passprice" type="text" name="passprice">
                <input class="regist__passdate" type="text" name="passdate">
                <input class="regist__pausecount" type="text" name="pausecount">
                <input class="regist__pausedate" type="text" name="pausedate">
                <!--제이쿼리 append사용하여 항목 추가-->
            </div>
            <input class="regist__gymnotice" type="text" name="notice"><br>
            <div class="regist__operationinfo">
                <input class="regist__gymoperatingtime" type="text" name="operatingtime"><br>
                <input class="regist__gymoperatingprogram" type="text" name="operatingprogram"><br>
                <input class="regist__gymextraservice" type="text" name="extraservice"><br>
                <input class="regist__etc" type="text" name="etc"><br>
            </div>
                <button type="submit" class="regist__gymsubmit">저장</button>
        </form>
    </div>

</body>
</html>