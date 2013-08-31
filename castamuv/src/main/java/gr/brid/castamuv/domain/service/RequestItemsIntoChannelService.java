package gr.brid.castamuv.domain.service;

import gr.brid.castamuv.domain.model.music.Music;
import gr.brid.castamuv.domain.model.playlist.PlayList;
import gr.brid.castamuv.domain.model.user.Channel;
import gr.brid.castamuv.domain.model.user.User;

public interface RequestItemsIntoChannelService {
	Channel request(Music music, PlayList list, Channel channel, long coin, User user);

	Channel request(PlayList list, Channel channel, long coin,  User user);
}
