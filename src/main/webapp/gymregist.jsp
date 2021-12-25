<%@page import="com.passgym.owner.vo.Owner"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>gymregist.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<meta charset="UTF-8">
<body>
<%
Owner sessionOwner = (Owner)session.getAttribute("signupInfo");
%>
����ID : <%=session.getId() %><br>
���ǳ��� : <%=sessionOwner.getOwnerNo() %><br>
����IS NEW : <%=session.isNew() %><br>
���� �������ð�: <%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(session.getLastAccessedTime())) %>
<div class="regist">
        <form class="regist__form" method="post" action="./gymregist">
            <div class="regist__header">
                <img class="regist__img" src=""><br>
                <div class="regist__gymname">
                <%= %>
                </div>
                <input class="regist__gymname" type="text" name="name" value="�ｺ�� �̸�" required><br>
                <input class="regist__gymaddr" type="text" name="addr" value="�ּ�" required><br>
            </div>
            <input class="regist__gymintroduce" type="text" name="introduce"><br>
            <input class="regist__gymnotice" type="text" name="notice"><br>
            <div class="regist__operationinfo">
                <input class="regist__gymoperatingtime" type="text" name="goperatingtime"><br>
                <input class="regist__gymoperatingprogram" type="text" name="operatingprogram"><br>
                <input class="regist__gymextraservice" type="text" name="extraservice"><br>
                <input class="regist__etc" type="text" name="etc"><br>
            </div>
            <a href="./login.html">
                <button type="submit" class="regist__gymsubmit">����</button>
            </a>
        </form>
    </div>

</body>
</html>