package gr.brid.castamuv.application.user.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.brid.castamuv.application.user.RequestService;
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
import gr.brid.castamuv.domain.service.RequestItemsIntoChannelService;

@Service
@Transactional
public class RequestServiceImpl implements RequestService {
	
	private MusicRepository musicRepository;
	
	private PlayListRepository playListRepository;
	
	private UserRepository userRepository;
	
	private RequestItemsIntoChannelService requestItemsIntoChannelService;
	
	@Override
	public void request(MusicId musicId, PlayListId playListId, ChannelId channelId, long coin, UserId userId) {
		Music music = musicRepository.findOne(musicId);
		PlayList list = playListRepository.findOne(playListId);
		Channel channel = userRepository.findByChannelId(channelId);
		User user = userRepository.findOne(userId);
		
		requestItemsIntoChannelService.request(music, list, channel, coin, user);
	}
	
	@Inject
	public void setRequestItemsIntoChannelService(
			RequestItemsIntoChannelService requestItemsIntoChannelService) {
		this.requestItemsIntoChannelService = requestItemsIntoChannelService;
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
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
