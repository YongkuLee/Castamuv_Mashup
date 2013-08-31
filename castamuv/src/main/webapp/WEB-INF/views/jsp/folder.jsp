<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width, height=device-height">
<title>Castamuv</title>
<script type="text/javascript" src="/resources/js/home.js"></script>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"
	type="text/javascript"></script>
<link type="text/css" rel="stylesheet"
	href="resources/css/castamov_style.css" />
</head>
<body>

	<div class="MusicFolderWrap">
		<div class="Topbar">
			<div class="TopbarButtonLeftWrap">
				<a onclick="window.history.back();"><div
						class="TopbarButtonLeft">Back</div></a>
			</div>
			<div class="TextTopbar">음악신청</div>
			<div class="TopbarButtonRightWrap">
				<a><div class="TopbarButtonRight">Search</div></a>
			</div>
		</div>
		<div class="MusicFolderProfileWrap">
			<div class="MusicFolderProfileBox">
				<div class="MusicFolderProfilePhoto">
					<img src="${user.image}" />
				</div>
				<div class="MusicFolderProfileText">
					bonosound Rock!!<br> <span
						style="color: #ed145b; font-size: 28px;">${user.username}</span>
				</div>

			</div>
		</div>
		<!-- List  -->
		<c:forEach items="${playlists}" var="playlist">
			<form action="/folder/${playlist.id.id}" id="${playlist.id.id}">
				<input type="hidden" name="channelId" value="${channelId}" />
				<div class="MusicFolderMusicListWrap"
					onclick="$('#${playlist.id.id}').submit();">
					<div class="MusicFolderMusicListTextWrap">${playlist.title}</div>
				</div>
			</form>
		</c:forEach>
		<!-- /List  -->
	</div>

</body>
</html>