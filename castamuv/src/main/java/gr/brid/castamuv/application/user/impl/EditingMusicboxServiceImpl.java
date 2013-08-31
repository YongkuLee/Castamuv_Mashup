package gr.brid.castamuv.application.user.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.brid.castamuv.application.user.EditingMusicboxService;
import gr.brid.castamuv.domain.model.music.Like;
import gr.brid.castamuv.domain.model.music.Music;
import gr.brid.castamuv.domain.model.music.MusicId;
import gr.brid.castamuv.domain.model.music.MusicRepository;
import gr.brid.castamuv.domain.model.playlist.PlayList;
import gr.brid.castamuv.domain.model.playlist.PlayListId;
import gr.brid.castamuv.domain.model.playlist.PlayListRepository;
import gr.brid.castamuv.domain.model.user.Channel;
import gr.brid.castamuv.domain.model.user.ChannelId;
import gr.brid.castamuv.domain.model.user.User;
import gr.brid.castamuv.domain.model.user.UserId;
import gr.brid.castamuv.domain.model.user.UserRepository;

@Service
@Transactional
public class EditingMusicboxServiceImpl implements EditingMusicboxService {

	private UserRepository userRepository;

	private PlayListRepository playListRepository;

	private MusicRepository musicRepository;

	@Override
	public Channel newChannel(UserId userId, String title) {
		User user = userRepository.findOne(userId);

		Channel channel = new Channel(user);

		channel.setTitle(title);

		user.getChannels().add(channel);

		userRepository.save(user);

		return channel;
	}

	@Override
	public void removeChannel(ChannelId channelId) {
		User user = userRepository.findByChannel(channelId);

		for (Channel channel : user.getChannels()) {
			if (channel.getId().sameValueOf(channelId)) {
				user.getChannels().remove(channel);
			}
		}

		userRepository.save(user);
	}

	@Override
	public PlayList newPlayList(String title, String description, UserId userId) {
		User user = userRepository.findOne(userId);

		PlayList playList = new PlayList(user);
		playList.setTitle(title);
		playList.setDescription(description);

		playListRepository.save(playList);

		user.getPlayLists().add(playList);

		userRepository.save(user);

		return playList;
	}

	@Override
	public void removePlayList(PlayListId plyaListId) {
		PlayList playList = playListRepository.findOne(plyaListId);

		for (PlayList list : playList.getEditor().getPlayLists()) {
			if (list.sameEntityAs(playList))
				playList.getEditor().getPlayLists().remove(list);
		}

		playListRepository.delete(playList);
	}

	@Override
	public void like(MusicId id, UserId userId) {
		Music music = musicRepository.findOne(id);

		User user = userRepository.findOne(userId);

		Like like = new Like(music, user);

		music.getLikes().add(like);

		musicRepository.save(music);
	}

	@Override
	public void unlike(MusicId id, UserId userId) {
		Music music = musicRepository.findOne(id);

		User user = userRepository.findOne(userId);

		Like like = new Like(music, user);

		for (Like likeOjb : music.getLikes()) {
			if (like.sameEntityAs(likeOjb)) {
				music.getLikes().remove(likeOjb);
			}
		}

		userRepository.save(user);

	}

	@Inject
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Inject
	public void setPlayListRepository(PlayListRepository playListRepository) {
		this.playListRepository = playListRepository;
	}

	@Inject
	public void setMusicRepository(MusicRepository musicRepository) {
		this.musicRepository = musicRepository;
	}
}
