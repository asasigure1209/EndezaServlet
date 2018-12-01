<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="java.util.*" %>
   <%@ page import="db.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcom to 陰スタグラム</title>
</head>
<body>
	<h1>Welcome to 陰スタグラム</h1>
	<table>
		<tr>
		<td>name</td>
		<td>email</td>
		<td>password</td>
		<tr>
		
<%
	ArrayList<userBean> list = (ArrayList<userBean>)request.getAttribute("userBeanList");
	for (int i = 0; i < list.size(); i++) {
		userBean ub = (userBean)list.get(i);
%>
		<tr>
		<td><%=ub.getName() %></td>
		<td><%=ub.getEmail() %></td>
		<td><%=ub.getPassword() %></td>
<%
	}
%>
	</table>
</body>
</html>