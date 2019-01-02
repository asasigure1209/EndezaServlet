<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="db.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>プロフィールの変更</title>
<link rel="stylesheet" type="text/css" href="./css/editData.css">
<link rel="stylesheet" href="./css/bulma.min.css">
</head>
<body cz-shortcut-listen="true">
<%
	userBean ub = (userBean)request.getAttribute("userBean");
	profileBean pb = (profileBean)request.getAttribute("profileBean");
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
                <a class="navbar-item">
                    <img src="" alt="Logo" width="112" height="28">
                </a>
            </div>

            <!--navbar-end でプロフィールボタンを左に寄せる-->
            <div class="navbar-end">
                <div class="navbar-item">
                    <a class="navbar-item">
                        <img src="" alt="Profile">
                    </a>
                </div>
            </div>
        </div>
  
  <div class="title">
    プロフィールの変更
  </div>
  
  <!--以下入力フォーム-->
  <form action="ProfileEditServlet" enctype="multipart/form-data" method="post">
    <article class="column is-offset-1 is-10" spellcheck="false">
      <div class="field">
        <label class="label">ユーザー名</label>
          <div class="control">
            <input class="input" type="text" placeholder="Name" value="<%=ub.getName() %>" name="username">
        </div>
      </div>
      
      <br>
   
      <div class="field">
        <label class="label">プロフィール画像</label>
          <input type="file" name="file" placeholder="picture">
      </div>
      <br>
      
      <div class="field">
        <label class="label">プロフィール</label>
        <div class="control">
          <input type="text" placeholder="Profile" value="<%=pb.getBio() %>" name="bio">
        </div>
      </div>
      <div class="field is-grouped is-grouped-right decide">
        <p class="control">
          <input type="submit" value="送信" class="button is-primary is-block">
        </p>
        <p class="control">
          <a class="button is-warning" href="myprofile.html">
            キャンセル
          </a>
        </p>
      </div>
      
    </article>
  </form>
</body>
</html>