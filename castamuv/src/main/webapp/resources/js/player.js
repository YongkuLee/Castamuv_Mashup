Player = {};

Player.musics = function() {
	var channelId = 0;
	var index = 0;
	var size = 0;
	var list = null;

	var initialize = function(_list) {
		list = _list;
		size = list.length;
	};

	var prev = function() {
		if (index > 0) {
			return list[index--];
		} else {
			return null;
		}
	};

	var next = function() {
		$.ajax({
			url : channelId + '/next',
			type : 'post'
		});

		if (index < size) {
			return list[index++];
		} else {
			return null;
		}
	};

	var load = function(callback) {
		$.ajax({
			type : 'post',
			success : function(data) {
				if (data.list) {
					initialize(data.list);
					callback(data.list, index);
				}
			}
		});
	};

	return {
		initialize : initialize,
		list : function() {
			return list;
		},
		size : function() {
			return size;
		},
		index : function() {
			return index;
		},
		next : next,
		prev : prev,
		load : load,
		setChannelId : function(_c) {
			channelId = _c;
		}
	};
};

Player.view = function() {
	var listDom = null;

	var doms = {};

	var makeDom = function(index, link, title, thumbnail) {
		var html = '<div>';
		html += '<input type="hidden" id="' + index + '"value="' + link
				+ '" />';
		html += '<div class="YoutubeListSubThumb">';
		if (thumbnail) {
			html += '<img src="' + thumbnail
					+ '" style="position:absolute;width:100%;height:100%;"/>';
		}
		html += '<i class="YoutubeListSubThumbIcon"></i>';
		html += '</div>';
		html += '<div class="YoutubeListSubThumbTitle">' + title + '</div>';
		html += '</div>';
		doms[index] = $(html);
		return doms[index];
	};

	var renderDoms = function() {
		listDom.empty();
		for ( var ind in doms) {
			listDom.append(doms[ind]);
		}
	};

	return {
		initialize : function(id) {
			listDom = $('#' + id);
		},
		render : function(musics) {
			var list = musics.list();
			doms = {};
			for ( var index in list) {
				var music = list[index];
				if (listDom != null) {
					console.log('Render list index : ' + index);
					makeDom(index, music.link.value, music.title,
							music.link.thumbnail);
					renderDoms();
				}
			}
			;
		}
	};
};

Player.play = function(index) {
	var id = $('#' + index).val();
	ytplayer.loadVideoById(id);
};

$(document).ready(
		function() {
			view = Player.view();
			view.initialize('list');

			musics = Player.musics();
			musics.setChannelId($('#ytapiplayer').html());
			musics.load(function(list, index) {
				view.render(musics);
				var start = $('#0').val();
				swfobject.embedSWF("http://www.youtube.com/v/" + start
						+ "?enablejsapi=1&playerapiid=ytplayer&version=3",
						"ytapiplayer", "100%", "100%", "8", null, null, params,
						atts);

				Player.play(index);
			});

			setInterval(function() {
				musics.load(function(list, index) {
					view.render(musics);
				});
			}, 2000);
		});

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

function prev() {
	if (musics.index() > 0) {
		musics.prev();
		Player.play(musics.index());
	}
}

function next() {
	if (musics.index() < musics.size()) {
		musics.next();
		Player.play(musics.index());
	}
}
