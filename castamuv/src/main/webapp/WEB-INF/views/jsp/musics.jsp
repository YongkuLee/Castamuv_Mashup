<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Castamuv</title>
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width, height=device-height">
<script src="http://code.jquery.com/jquery-1.10.1.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="/resources/js/home.js"></script>
<link type="text/css" rel="stylesheet"
	href="/resources/css/castamov_style.css" />
</head>
<body>

	<div class="StoreMusicListWrap">
		<div class="Topbar">
			<div class="TopbarButtonLeftWrap">
				<a onclick="window.history.back();"><div
						class="TopbarButtonLeft">Back</div></a>
			</div>
			<div class="TextTopbar">음악신청</div>
			<div class="TopbarButtonRightWrap">
				<!-- 	<a><div class="TopbarButtonRight PointColorYellow">Coin</div></a> -->
			</div>
		</div>
		<!-- List  -->
		<c:forEach items="${musics}" var="music">
			<form action="/request" id="${music.id.id}">
				<input type="hidden" name="channelId" value="${channelId}" /> <input
					type="hidden" name="musicId" value="${music.id.id}" /> <input
					type="hidden" name="playListId" value="${playlistId}" />
				<div class="StoreMusicListMusicListWrap"
					onclick="$('#${music.id.id}').submit();">
					<div class="StoreMusicListMusicListSubWrap">
						<div class="StoreMusicListMusicListTextWrap">
							<div>${music.title}</div>
							<div style="padding-top: 10px;">
								<i class="StoreMusicListMusicListLike"></i><span
									style="padding-left: 10px;">5,634</span>
							</div>
						</div>
						<div class="StoreMusicListMusicListThumb"
							style="overflow: hidden;">
							<img src="${music.link.thumbnail}" style="width: 100%;" />
						</div>
					</div>
				</div>
			</form>
		</c:forEach>
		<!-- /List  -->
	</div>

</body>
</html>