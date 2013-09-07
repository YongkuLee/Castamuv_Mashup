package gr.brid.castamuv.infrastructure.request;

import gr.brid.castamuv.domain.model.music.Music;
import gr.brid.castamuv.domain.model.playlist.PlayList;
import gr.brid.castamuv.domain.model.user.Channel;
import gr.brid.castamuv.domain.model.user.Stream;
import gr.brid.castamuv.domain.model.user.User;
import gr.brid.castamuv.domain.service.RequestItemsIntoChannelService;

import org.springframework.stereotype.Service;

@Service
public class DefaultRequestItemsIntoChannelService implements
		RequestItemsIntoChannelService {
	
	@Override
	public Channel request(Music music, PlayList list, Channel channel,
			long coin, User user) {
		Stream stream = new Stream(channel.getId(), music.getId(),
				list.getId(), user.getId());
		channel.getStreams().add(stream);
		return channel;
	}

	@Override
	public Channel request(PlayList list, Channel channel, long coin, User user) {
		for (Music music : list.getMusics()) {
			Stream stream = new Stream(channel.getId(), music.getId(),
					list.getId(), user.getId());
			channel.getStreams().add(stream);
		}
		return channel;
	}

}
