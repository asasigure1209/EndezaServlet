<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="db.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> TimeLine | 陰スタグラム</title>
</head>
<body>
	<h1>TimeLine</h1>
	
	<form action="PostServlet" method="get">
		<input type="text" name="text">
		<input type="submit" value="submit">
	</form>
<%
	ArrayList<postBean> postList = (ArrayList<postBean>)request.getAttribute("postBeanList");
	for (int i=0; i<postList.size(); i++) {
		postBean pb = (postBean)postList.get(i);
%>
	<p><%=pb.getId() %></p>
	<p><%=pb.getUser() %></p>
	<p><%=pb.getText() %></p>
	<p><%=pb.getCreatedAt() %></p>
<%
	}
%>
	<form action="LogoutServlet" method="GET">
		<input type="submit" value="logout">
	</form>
</body>
</html>