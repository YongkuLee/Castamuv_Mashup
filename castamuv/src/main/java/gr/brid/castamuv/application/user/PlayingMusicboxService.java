package gr.brid.castamuv.application.user;

import gr.brid.castamuv.domain.model.music.MusicId;
import gr.brid.castamuv.domain.model.user.ChannelId;
import gr.brid.castamuv.domain.model.user.UserId;

public interface PlayingMusicboxService {
	void play(UserId userId);

	void play(MusicId musicId, UserId userId);

	void play(ChannelId channelId, UserId userId);
}
