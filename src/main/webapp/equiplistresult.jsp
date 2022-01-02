<%@page import="com.passgym.equip.vo.Equip"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%><%
    List<Equip>list = (List)request.getAttribute("list");
%>[    
<%  
for(int i=0; i<list.size(); i++){
	Equip e	= list.get(i);
	if(i > 0){
%>,
<%  }
%>
{
 "equipNo":<%=e.getEquipNo()%>,    	
 "equipName":"<%=e.getEquipName()%>"
}
<%
}//end for
%>
]