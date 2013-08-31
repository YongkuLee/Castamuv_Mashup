package gr.brid.castamuv.application.user;

import gr.brid.castamuv.domain.model.music.MusicId;
import gr.brid.castamuv.domain.model.playlist.PlayList;
import gr.brid.castamuv.domain.model.playlist.PlayListId;
import gr.brid.castamuv.domain.model.user.Channel;
import gr.brid.castamuv.domain.model.user.ChannelId;
import gr.brid.castamuv.domain.model.user.UserId;

public interface EditingMusicboxService {
	Channel newChannel(UserId userId, String title);

	void removeChannel(ChannelId channelId);

	PlayList newPlayList(String title, String description, UserId userId);

	void removePlayList(PlayListId plyaListId);

	void like(MusicId id, UserId userId);

	void unlike(MusicId id, UserId userId);
}
