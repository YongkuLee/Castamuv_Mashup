package gr.brid.castamuv.application.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.brid.castamuv.application.user.UserMusicboxGettingService;
import gr.brid.castamuv.domain.model.music.Music;
import gr.brid.castamuv.domain.model.music.MusicId;
import gr.brid.castamuv.domain.model.music.MusicRepository;
import gr.brid.castamuv.domain.model.playlist.PlayList;
import gr.brid.castamuv.domain.model.playlist.PlayListId;
import gr.brid.castamuv.domain.model.playlist.PlayListRepository;
import gr.brid.castamuv.domain.model.user.Channel;
import gr.brid.castamuv.domain.model.user.ChannelId;
import gr.brid.castamuv.domain.model.user.Status;
import gr.brid.castamuv.domain.model.user.Stream;
import gr.brid.castamuv.domain.model.user.User;
import gr.brid.castamuv.domain.model.user.UserId;
import gr.brid.castamuv.domain.model.user.UserRepository;

@Service
@Transactional
public class UserMusicboxGettingServiceImpl implements
		UserMusicboxGettingService {

	private UserRepository userRepository;

	private PlayListRepository playListRepository;

	private MusicRepository musicRepository;

	@Override
	public List<Channel> channels(UserId id) {
		List<Channel> channels = new ArrayList<Channel>();
		User user = userRepository.findOne(id);
		for (Channel channel : user.getChannels()) {
			channels.add(channel);
		}
		return channels;
	}

	@Override
	public List<PlayList> playLists(UserId id) {
		List<PlayList> r = new ArrayList<PlayList>();
		User user = userRepository.findOne(id);
		for (PlayList list : user.getPlayLists()) {
			r.add(list);
		}
		return r;
	}

	@Override
	public List<Music> musics(PlayListId id) {
		List<Music> r = new ArrayList<Music>();
		PlayList playList = playListRepository.findOne(id);
		for (Music music : playList.getMusics()) {
			r.add(music);
		}
		return r;
	}

	@Override
	public List<Music> musics(ChannelId channelId) {
		List<Music> musics = new ArrayList<Music>();
		Channel channel = userRepository.findByChannelId(channelId);

		for (Stream stream : channel.getCurrentStreamList()) {
			Music music = musicRepository.findOne(stream.getMusicId());
			music.setLikes(null);
			if (music != null)
				musics.add(music);
		}
		return musics;
	}

	@Override
	public Stream next(ChannelId channelId) {
		Channel channel = userRepository.findByChannelId(channelId);
		Stream stream = channel.nextStream();
		userRepository.save(channel.getUser());
		return stream;
	}

	@Override
	public List<Music> likes(UserId id) {
		User user = userRepository.findOne(id);
		return musicRepository.findByUser(user);
	}

	@Override
	public Status status(UserId id) {
		User user = userRepository.findOne(id);
		return user.getStatus();
	}

	@Override
	public Music music(MusicId musicId) {
		return musicRepository.findOne(musicId);
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

	@Override
	public User user(UserId userId) {
		return userRepository.findOne(userId);
	}

}
