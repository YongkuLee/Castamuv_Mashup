package gr.brid.castamuv.application.user;

import gr.brid.castamuv.domain.model.music.MusicId;
import gr.brid.castamuv.domain.model.playlist.PlayListId;
import gr.brid.castamuv.domain.model.user.ChannelId;
import gr.brid.castamuv.domain.model.user.UserId;

public interface RequestService {
	void request(MusicId musicId, PlayListId playListId, ChannelId channelId, long coin, UserId userId);
}
