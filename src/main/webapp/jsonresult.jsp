<%@ page contentType="application/json; charset=UTF-8"%>
<%
int status = (Integer)request.getAttribute("status");
String msg = (String)request.getAttribute("msg");
int ownerStatus = (Integer)request.getAttribute("ownerStatus");
%>
{"status":<%=status%>, "msg":"<%=msg%>", "ownerStatus":"<%=ownerStatus%>" }