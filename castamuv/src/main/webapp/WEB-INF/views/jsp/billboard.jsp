<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello World!</title>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="/resources/js/home.js"></script>
</head>
<body>
	<h1>Billboards</h1>
	<h2>${auth.principal}</h2>
	<c:forEach items="${list}" var="billboard">
		<div>
			<p>${billboard.title}</p>
		</div>
	</c:forEach>
</body>
</html>
