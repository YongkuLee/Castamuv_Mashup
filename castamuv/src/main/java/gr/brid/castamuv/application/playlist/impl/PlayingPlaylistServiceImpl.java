package gr.brid.castamuv.application.playlist.impl;

import javax.inject.Inject;

import gr.brid.castamuv.application.playlist.PlayingPlaylistService;
import gr.brid.castamuv.domain.model.music.Music;
import gr.brid.castamuv.domain.model.music.MusicId;
import gr.brid.castamuv.domain.model.music.MusicRepository;
import gr.brid.castamuv.domain.model.playlist.PlayList;
import gr.brid.castamuv.domain.model.playlist.PlayListId;
import gr.brid.castamuv.domain.model.playlist.PlayListRepository;
import gr.brid.castamuv.domain.model.user.User;
import gr.brid.castamuv.domain.model.user.UserId;
import gr.brid.castamuv.domain.model.user.UserRepository;
import gr.brid.castamuv.domain.service.PlayMusicService;

public class PlayingPlaylistServiceImpl implements PlayingPlaylistService {

	private MusicRepository musicRepository;

	private PlayListRepository playListRepository;

	private PlayMusicService playMusicService;

	private UserRepository userRepository;

	@Override
	public void play(MusicId musicId, PlayListId playListId, UserId userId) {
		Music music = musicRepository.findOne(musicId);

		PlayList playList = playListRepository.findOne(playListId);

		User user = userRepository.findOne(userId);

		playMusicService.play(music, playList, user);
	}

	@Override
	public void stop(MusicId musicId, PlayListId playListId, UserId userId) {
		Music music = musicRepository.findOne(musicId);

		PlayList playList = playListRepository.findOne(playListId);

		User user = userRepository.findOne(userId);

		playMusicService.stop(music, playList, user);
	}

	@Inject
	public void setMusicRepository(MusicRepository musicRepository) {
		this.musicRepository = musicRepository;
	}

	@Inject
	public void setPlayListRepository(PlayListRepository playListRepository) {
		this.playListRepository = playListRepository;
	}

	@Inject
	public void setPlayMusicService(PlayMusicService playMusicService) {
		this.playMusicService = playMusicService;
	}

}
