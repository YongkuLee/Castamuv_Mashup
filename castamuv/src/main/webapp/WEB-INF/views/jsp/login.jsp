<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello World!</title>
<script type="text/javascript" src="/resources/js/home.js"></script>
</head>
<body>
	<h1>Hello world!</h1>
	<h2>${auth.principal}</h2>
	<form name="loginForm" method="post" action="/j_spring_security_check">
         <fieldset>
             <legend>로그인</legend>
             <p class="userid"><input name="j_username" id="mbrId" type="text" style="width:125px;" class="basic" placeholder="아이디" /></p>
             <p class="password"><input name="j_password" id="pwd" style="width:125px;" class="basic" type="password" placeholder="비밀번호" size="20" /></p>
             <p class="saveid"><input id="save_id" name="_spring_security_remember_me" type="checkbox" onclick="jsSaveId();"/> <label for="save_id">로그인 상태 유지</label></p>
             <input type="submit"/>
         </fieldset>
     </form>
     <div>
     	<a href="/signup">가입하기</a>
     </div>
</body>
</html>