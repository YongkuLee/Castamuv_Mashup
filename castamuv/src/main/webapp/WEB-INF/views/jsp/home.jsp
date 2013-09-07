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
	href="/resources/css/castamov_style_web.css" />
<script type="text/javascript"
	src="/resources/js/swfobject/swfobject.js"></script>
<script type="text/javascript" src="/resources/js/player.js"></script>

<style type="text/css">
#tvBox {
	width: 100%;
	height: 100%;
}
</style>

</head>

<body>

	<div class="YoutubeWrap">
		<div id="tvBox">
			<div id="ytapiplayer">${channelId}</div>
		</div>
		<a><div class="Door"></div></a>
		<div class="YoutubeListWrap">
			<div class="Logo02">
				<div class="YoutubeListSubWrap" id="list"></div>
			</div>
		</div>
	</div>

</body>
</html>
