<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello World!</title>
<script type="text/javascript" src="/resources/js/home.js"></script>
</head>
<body>
	<h1>Sign Up</h1>
	<div>
		<h4>Users List</h4>
		<c:forEach items="${emails}" var="email">
			<p>${email}</p>
		</c:forEach>
	</div>
	<form method="post">
		<input name="email" type="text" placeholder="email" /> <input
			name="password" type="password" placeholder="password" /> <input
			type="submit" />
	</form>
</body>
</html>