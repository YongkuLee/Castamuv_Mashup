package gr.brid.castamuv.application.user.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.brid.castamuv.application.user.PlayingMusicboxService;
import gr.brid.castamuv.domain.model.music.Music;
import gr.brid.castamuv.domain.model.music.MusicId;
import gr.brid.castamuv.domain.model.music.MusicRepository;
import gr.brid.castamuv.domain.model.user.Channel;
import gr.brid.castamuv.domain.model.user.ChannelId;
import gr.brid.castamuv.domain.model.user.User;
import gr.brid.castamuv.domain.model.user.UserId;
import gr.brid.castamuv.domain.model.user.UserRepository;

@Service
@Transactional
public class PlayingMusicboxServiceImpl implements PlayingMusicboxService {

	private UserRepository userRepository;
	private MusicRepository musicRepository;

	@Override
	public void play(UserId userId) {
		User user = userRepository.findOne(userId);
		if (user.getStatus().getMusic() != null)
			user.getStatus().setPlay(true);
	}

	@Override
	public void play(MusicId musicId, UserId userId) {
		User user = userRepository.findOne(userId);
		Music music = musicRepository.findOne(musicId);
		user.getStatus().setPlay(true);
		user.getStatus().setMusic(music);
		userRepository.save(user);
	}

	@Override
	public void play(ChannelId channelId, UserId userId) {
		User user = userRepository.findOne(userId);
		user.getStatus().setPlay(true);
		for (Channel channel : user.getChannels()) {
			Music music = musicRepository.findOne(channel.getCurrentStream()
					.getMusicId());
			if (music != null)
				user.getStatus().setMusic(music);
		}
		userRepository.save(user);
	}

	@Inject
	public void setMusicRepository(MusicRepository musicRepository) {
		this.musicRepository = musicRepository;
	}

	@Inject
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

}
