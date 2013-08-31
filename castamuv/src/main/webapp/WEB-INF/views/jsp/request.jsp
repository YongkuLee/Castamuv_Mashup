<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet"
	href="resources/css/castamov_style.css" />

</head>

<body>

	<div class="StoreMusicUWrap">
		<div class="Topbar">
			<div class="TopbarButtonLeftWrap">
				<a><div class="TopbarButtonLeft"
						onclick="window.history.back();">Back</div></a>
			</div>
			<div class="TextTopbar">음악신청</div>
			<div class="TopbarButtonRightWrap">
				<!-- 	<a><div class="TopbarButtonRight PointColorYellow">Coin</div></a> -->
			</div>
		</div>
		<div class="StoreMusicUBotbarWrap">
			<a><div class="StoreMusicUBotbarBtnLikeOff"></div></a> <a><div
					class="StoreMusicUBotbarBtnScrap"></div></a>
			<form id="requestForm" method="post" action="/request">
				<input type="hidden" name="musicId" value="${music.id.id}" /> <input
					type="hidden" name="playListId" value="${playListId}" /> <input
					type="hidden" name="channelId" value="${channelId}" /> <a
					onclick="$('#requestForm').submit();"><input type="submit"
					class="StoreMusicUBotbarBtnMusic" /></a>
			</form>
		</div>

	</div>
	<div class="StoreMusicUSubWrap">
		<iframe src="//www.youtube.com/embed/${music.link.value}" width="100%"
			height="100%"></iframe>
	</div>
	</div>

</body>
</html>
