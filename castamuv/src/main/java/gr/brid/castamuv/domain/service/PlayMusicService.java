package gr.brid.castamuv.domain.service;

import gr.brid.castamuv.domain.model.music.Music;
import gr.brid.castamuv.domain.model.playlist.PlayList;
import gr.brid.castamuv.domain.model.user.User;

public interface PlayMusicService {
	void play(Music music, PlayList playList, User user);
	void stop(Music music, PlayList playList, User user);
}
