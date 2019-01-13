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
<script src="./js/jquery-3.3.1.min.js"></script>
</head>
<body cz-shortcut-listen="true">
<% 
	ArrayList<userBean> userList = (ArrayList<userBean>)request.getAttribute("userBeanList");
	userBean ub = userList.get(0);
	profileBean myUserProfileBean = new profileBean();
	myUserProfileBean.getProfileByProfileId(ub.getProfile());
%>

	<!--ヘッダー部分-->
    <div class="navbar is-inline-flex is-transparent">
        <!--navbar-start で投稿ボタンとロゴを右に寄せる-->
        <div class="navbar-start">
            <div class="navbar-item">
                <a class="navbar-item button" id="open">
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
                <a class="navbar-item" href="ProfileServlet?user=<%=ub.getId() %>">
                    <img src="./img/profile.png" alt="Profile">
                </a>
            </div>
        </div>
    </div>
	
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
         <div class="content">
             <%=pb.getText() %>
         </div>
         <time><%=pb.getCreatedAt() %></time>
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
	
	<div class="modal">
      <div class="modal-background"></div>
      <div class="modal-content">
          <div class="box">
              <div class="media">
                  <figure class="media-left">
                      <p class="image is-64x64">
                      <img class="is-rounded" src="./ImageFileServlet?name=<%=myUserProfileBean.getImage() %>">
                      </p>
                  </figure>
                  <form method="POST" action="./PostServlet" enctype="multipart/form-data">
                  <div class="media-content">
                      <div class="field">
                           	<p>
                               <div class="file">
                                   <label class="file-label">
                                       <input class="file-input" type="file" name="file">
                                       <span class="file-cta">
                                       <span class="file-label">
                                           Choose a file
                                       </span>
                                       </span>
                                   </label>
                               </div>
                           	</p>
                           <p class="control">
                               <textarea name="text" id="text-box" class="textarea" placeholder="Add a comment..." rows="5"></textarea>
                           </p>
                      </div>
                  </div>
                  <input type="submit" value="Share" class="button is-info media-right">
                  </form>
              </div>
          </div>
      </div>
   <button id="close" class="modal-close is-large" aria-label="close"></button>
</div>
            
<script src="./js/modal.js"></script>
<script src="./js/postCheck.js"></script>
</body>
</html>