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
	<h1>PlayLists</h1>
	<h2>${auth.principal}</h2>
	<c:forEach items="${playlists}" var="playlist">
		<div>
			<a href="/playlists/${playlist.id.id}"><p>${playlist.title}</p></a>
			<p>${playlist.description}</p>
		</div>
	</c:forEach>

	<form method="post" action="/playlists">
		<input type="text" name="title" /> <input type="text"
			name="description" /> <input type="submit" />
	</form>

	<c:forEach items="${musics}" var="music">
		<div>
			<p>${music.title}</p>
			<p>${music.description}</p>
			<p>
				<img src="${music.link.thumbnail}" />
			</p>
		</div>
	</c:forEach>

	<form method="post" action="/playlists">
		<input type="text" name="url" placeholder="url" /> <input type="text"
			name="title" placeholder="title" /> <input type="text"
			name="description" placeholder="description" /> <input type="submit" />
	</form>

</body>
</html>