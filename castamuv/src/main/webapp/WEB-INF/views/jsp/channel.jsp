<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width, height=device-height">
<title>Castmuv</title>
<link type="text/css" rel="stylesheet"
	href="/resources/css/castamov_style.css" />

<script src="http://code.jquery.com/jquery-1.10.1.min.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="http://apis.daum.net/maps/maps3.js?apikey=360504c1904d4edfb97e61cecce482c3b16fcba6"></script>


</head>
<body>
	<div class="StoreNowWrap">
		<div class="Topbar">
			<div class="TopbarButtonLeftWrap">
				<a href="/playlists"><div class="TopbarButtonLeft">Menu</div></a>
			</div>
			<div class="LogoTopbar"></div>
			<div class="TopbarButtonRightWrap">
				<a><div class="TopbarButtonRight PointColorYellow">Coin</div></a>
			</div>
		</div>
		<div class="StoreNowMapWrap">
			<div class="StoreNowMapPlaceBox">
				<div class="StoreNowMapPlaceName">
					</i>탐엔탐스 양재점(id:yangjaetom)
				</div>
			</div>
			<div class="StoreNowMapBox" id="map">
				지도 들어가는 자리<br>지도 들어가는 자리<br>지도 들어가는 자리<br>지도 들어가는 자리<br>지도
				들어가는 자리<br>지도 들어가는 자리<br>지도 들어가는 자리<br>
			</div>
		</div>
		<div class="StoreNowTabButtonBG">
			<table
				style="height: 90px; width: 100%; text-align: center; font-size: 30px;">
				<tr>
					<td style="border-right: 1px solid #323232; color: #ed145b;">재
						생 중</td>

					<td style="border-right: 1px solid #323232;">재생목록</td>
					<form action="/folder" id="request">
						<input type="hidden" name="channelId" value="${channelId}" />
						<td onclick="$('#request').submit();">음악신청</td>
					</form>
				</tr>
			</table>
		</div>
		<!-- List  -->
		<c:forEach items="${musics}" var="music">
			<div class="StoreNowMusicListWrap">
				<div class="StoreNowMusicListSubWrap">
					<div class="StoreNowMusicListTextWrap">
						<div>${music.title}</div>
						<div style="padding-top: 10px;">
							<i class="StoreNowMusicListLike"></i><span
								style="padding-left: 10px;">5,634</span>
						</div>
					</div>
					<div class="StoreNowMusicListThumb">
						<img src="${music.link.thumbnail}" />
					</div>
				</div>
			</div>
		</c:forEach>
		<!-- /List  -->
	</div>
	<script type="text/javascript">
		map = new daum.maps.Map(document.getElementById('map'), {
			center: new daum.maps.LatLng(37.537123, 127.005523)
		});

		var marker = new daum.maps.Marker({
			<c:forEach items="${channels}" var="channel" varStatus="status">
			position: new daum.maps.LatLng(${channel.location.lat}, ${channel.location.lng})
				,
			</c:forEach>
		});
		
		marker.setMap(map);
	</script>
</body>
</html>