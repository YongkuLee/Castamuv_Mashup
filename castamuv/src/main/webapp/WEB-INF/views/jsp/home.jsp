<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="http://code.jquery.com/jquery-1.10.1.min.js"
	type="text/javascript"></script>
<link type="text/css" rel="stylesheet"
	href="resources/css/castamov_style_web.css" />
<script type="text/javascript"
	src="/resources/js/swfobject/swfobject.js"></script>

</head>

<body>

	<div class="YoutubeWrap">
		<a><div class="Door" id="ytapiplayer"></div></a>
		<div class="YoutubeListWrap">
			<div class="Logo02">
				<div class="YoutubeListSubWrap">
					<!--  List -->
					<c:forEach items="${musics}" var="music" varStatus="status">
						<div>
							<input type="hidden" id="${status.index}"
								value="${music.link.value}" />
							<div class="YoutubeListSubThumb">
								<i class="YoutubeListSubThumbIcon"></i>
							</div>
							<div class="YoutubeListSubThumbTitle">${music.title}</div>
						</div>
					</c:forEach>
					<!--  /List -->
				</div>
			</div>
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
		var start = $('#0').val();
		swfobject.embedSWF("http://www.youtube.com/v/" + start
				+ "?enablejsapi=1&playerapiid=ytplayer&version=3",
				"ytapiplayer", "425", "356", "8", null, null, params, atts);

		var max = $('#list').children().size();
		play(playList);
	</script>

</body>
</html>
