<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="db.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="./css/myprofileData.css">
<link rel="stylesheet" href="./css/bulma.min.css">
<title>Profile</title>
</head>
<body cz-shortcut-listen="true">
<%
	userBean ub = (userBean)request.getAttribute("userBean");
	userBean myUb = (userBean)request.getAttribute("myUserBean");
	profileBean profilebean = new profileBean();
	profilebean.getProfileByProfileId(ub.getProfile());
	ArrayList<postBean> postList = (ArrayList<postBean>)request.getAttribute("postBeanList");
%>

 <!--ヘッダー部分-->
	<div class="navbar is-inline-flex is-transparent">
	    <!--navbar-start で投稿ボタンとロゴを右に寄せる-->
	    <div class="navbar-start" style="visibility: hidden">
	        <div class="navbar-item">
	            <a class="navbar-item">
	                <img src="./img/post.png" alt="Post">
	            </a>
	        </div>
	    </div>
	    <!--navbar-brand でロゴとして指定する-->
	    <div class="navbar-brand">
	        <a class="navbar-item" href="TimeLineServlet">
	            <img src="./img/logo.png" alt="Logo" width="112" height="28">
	        </a>
	    </div>
	
	    <!--navbar-end でプロフィールボタンを左に寄せる-->
	    <div class="navbar-end">
	        <div class="navbar-item">
	            <a class="navbar-item">
	                <img src="./img/profile.png" alt="Profile">
	            </a>
	        </div>
	    </div>
	</div>
	
	  <div class="info">
	    <div class="columns">
	    <div class="column is-one-fifths">
	      <article class="media">
	        
	        <!--プロフィール画像-->
	        <figure class="media-left">
	          <p class="image is-128x128" style="width:132;
	                        border-radius:300px;
	                        border:5px solid #fff;
	                        background:url(./ImageFileServlet?name=<%=profilebean.getImage() %>) center center">
	            
	          </p>
	        </figure>
	        
	        <!--プロフィール情報-->
	        <div class="media-content">
	         <div class="content">
	           <!--名前とID-->
	          <p class="profile">
	            <font class="title">
	            <%=ub.getName() %>
	            </font>
<%
	if (ub.getId().equals(myUb.getId()))
	{
%>
	            <a   class="button is-info myprofile" href="./ProfileEditServlet">
	            プロフィールの編集
	            </a>
<%
	}
%>
	          </p>
	           <p class="text">
	           <!--投稿数-->
	            <font class="post"> 
	            投稿　<%=postList.size()%>件<br>
	             </font>
	           <!--一言-->
	            	<%=profilebean.getBio() %>
	           </p>
	         </div>
	         
	        </div>
	        
	      </article>
	      <hr>
	      
	      <!-- 投稿 -->
<%
for (int i = 0; i < postList.size(); i++) {
	postBean pb = (postBean)postList.get(i);
%>
	      <a href="#" class="rink">
	      	<figure>
	      		<img class="picture" src="./ImageFileServlet?name=<%=pb.getImage()%>">
	      	</figure>
	      </a>
<%
}
%>
	      </div>
	    </div>
	  </div>

</body>
</html>