<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="db.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> TimeLine | 陰スタグラム</title>

<link rel="stylesheet" type="text/css" href="./css/instaAlbum.css">
<link rel="stylesheet" href="./css/bulma.min.css">
</head>
<body cz-shortcut-listen="true">
<% 
	ArrayList<userBean> userList = (ArrayList<userBean>)request.getAttribute("userBeanList");
	userBean ub = userList.get(0);
%>

	<!--ヘッダー部分-->
    <div class="navbar is-inline-flex is-transparent">
        <!--navbar-start で投稿ボタンとロゴを右に寄せる-->
        <div class="navbar-start">
            <div class="navbar-item">
                <a class="navbar-item">
                    <img src="" alt="Post">
                </a>
            </div>
        </div>
        <!--navbar-brand でロゴとして指定する-->
        <div class="navbar-brand">
            <a class="navbar-item" href="TimeLineServlet">
                <img src="" alt="Logo" width="112" height="28">
            </a>
        </div>

        <!--navbar-end でプロフィールボタンを左に寄せる-->
        <div class="navbar-end">
            <div class="navbar-item">
                <a class="navbar-item" href="ProfileServlet?user=<%=ub.getId() %>">
                    <img src="" alt="Profile">
                </a>
            </div>
        </div>
    </div>
	
	<form action="PostServlet" enctype="multipart/form-data" method="post">
		<input type="text" name="text">
		<input type="file" name="file">
		<input type="submit" value="submit">
	</form>
	
	<!--メイン部分-->
    <div class="columns body-columns">
    <div class="column is-half is-offset-one-quarter">	
<%
	ArrayList<postBean> postList = (ArrayList<postBean>)request.getAttribute("postBeanList");
	for (int i=0; i<postList.size(); i++) {
		postBean pb = (postBean)postList.get(i);
		ub = new userBean();
		ub.getUserById(pb.getUser());
		profileBean profilebean = new profileBean();
		profilebean.getProfileByProfileId(ub.getProfile());
%>	
	<div class="card">
     <div class="header">
         <div class="media">
             <div class="media-left">
                 <figure class="image is-48x48">
                     <img class="is-rounded" src="./ImageFileServlet?name=<%=profilebean.getImage() %>">
                 </figure>
             </div>
             <div class="media-content">
                 <a class="title is-4" href="ProfileServlet?user=<%=ub.getId() %>"><%=ub.getName() %></a>
             </div>
         </div>
     </div>
     <div class="card-image">
         <figure class="image is-4by3">
             <img src="./ImageFileServlet?name=<%=pb.getImage() %>" alt="Placeholder image">
         </figure>
     </div>
     <div class="card-content">
         <div class="level is-mobile">
             <div class="level-left">
                 <div class="level-item has-text-centered">
                     <a href>
                         <i class="material-icons">favorite_border</i>
                     </a>
                 </div>
             </div>
         </div>
         <div class="content">
             <p>
                 <strong>32 Likes</strong>
             </p>
             <%=pb.getText() %>
         </div>
         <time datetime="2018-1-1"><%=pb.getCreatedAt() %></time>
     </div>
 </div>
<%
	}
%>

	</div>
	</div>
	<form action="LogoutServlet" method="GET">
		<input type="submit" value="logout">
	</form>
</body>
</html>