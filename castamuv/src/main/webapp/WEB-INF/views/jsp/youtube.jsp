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
<script type="text/javascript"
	src="/resources/js/swfobject/swfobject.js"></script>
</head>
<body>
	<div id="screen" style="position: fixed; float: left;">
		<h1>Youtubes</h1>
		<h2>${auth.principal}</h2>
		<div id="tvBox">
			<div id="ytapiplayer">You need Flash player 8+ and JavaScript
				enabled to view this video.</div>
		</div>

		<button onclick="prev(); return false;">Prev</button>
		<button onclick="play(playList); return false;">Play</button>
		<button onclick="next(); return false;">Next</button>
	</div>
	<div id="menu" style="margin-left: 450px;">
		<form>
			<select name="playlistId">
				<c:forEach items="${playlists}" var="playlist">
					<option value="${playlist.id.id}">${playlist.title}</option>
				</c:forEach>
			</select> <input type="text" name="text" /> <input type="submit"
				value="search" />
		</form>
		<div id="list">
			<c:forEach items="${list}" var="youtube" varStatus="status">
				<form action="/playlists/${playlistId}" method="post">
					<div>
						<p id="${status.index}">${youtube.id}</p>
						<p>${youtube.title}</p>
						<p>
							<img src="${youtube.thumbnail}" />
						</p>
						<input type="hidden" name="title" value="${youtube.title}" /> <input
							type="hidden" name="url" value="${youtube.id}" /> <input
							type="hidden" name="description" value="${youtube.title}" /> <input
							type="submit" value="리스트에 추가" />
					</div>
				</form>
			</c:forEach>
		</div>
	</div>
	<script type="text/javascript">
		var playList = 0;
		var params = {
			allowScriptAccess : "always"
		};
		var atts = {
			id : "myytplayer"
		};

		function onYouTubePlayerReady(playerId) {
			ytplayer = document.getElementById("myytplayer");
			ytplayer.addEventListener("onStateChange", "onytplayerStateChange");
			ytplayer.playVideo();
		}

		function onytplayerStateChange(newState) {
			if (newState == 0)
				next();
		}
		function remove() {
			$('#myytplayer').remove();
			$('#tvBox').append('<div id="ytapiplayer"></div>');
		}

		function play(number) {
			var id = $('#' + number).html();
			ytplayer.loadVideoById(id);
		}

		function prev() {
			if (playList > 0) {
				play(--playList);
			}
		}

		function next() {
			if (playList < max) {
				play(++playList);
			}
		}
		var start = $('#0').html();
		swfobject.embedSWF("http://www.youtube.com/v/" + start
				+ "?enablejsapi=1&playerapiid=ytplayer&version=3",
				"ytapiplayer", "425", "356", "8", null, null, params, atts);

		var max = $('#list').children().size();
		play(playList);
	</script>
</body>
</html>