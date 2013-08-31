package gr.brid.castamuv.application.playlist;

import gr.brid.castamuv.domain.model.music.MusicId;
import gr.brid.castamuv.domain.model.playlist.PlayListId;
import gr.brid.castamuv.domain.model.user.UserId;

public interface PlayingPlaylistService {
	void play(MusicId musicId, PlayListId playListId, UserId userId);
	void stop(MusicId musicId, PlayListId playListId, UserId userId);
}
