package gr.brid.castamuv.infrastructure.play;

import org.springframework.stereotype.Service;

import gr.brid.castamuv.domain.model.music.Music;
import gr.brid.castamuv.domain.model.playlist.PlayList;
import gr.brid.castamuv.domain.model.user.Status;
import gr.brid.castamuv.domain.model.user.User;
import gr.brid.castamuv.domain.service.PlayMusicService;

@Service
public class UserPlayMusicService implements PlayMusicService {

	@Override
	public void play(Music music, PlayList playList, User user) {
		Status status = new Status();
		status.setMusic(music);
		status.setPlayList(playList);
		status.setPlay(true);
		user.setStatus(status);
	}

	@Override
	public void stop(Music music, PlayList playList, User user) {
		Status status = new Status();
		status.setMusic(music);
		status.setPlayList(playList);
		status.setPlay(false);
		user.setStatus(status);
	}
}
