package gr.brid.castamuv.application.playlist;

import gr.brid.castamuv.domain.model.music.Music;
import gr.brid.castamuv.domain.model.music.MusicId;
import gr.brid.castamuv.domain.model.playlist.PlayListId;

public interface EditingPlaylistService {
	Music addMusic(String url, String title, String description,
			PlayListId playListId);

	void deleteMusic(MusicId musicId, PlayListId playListId);
}
