package gr.brid.castamuv.application.user;

import gr.brid.castamuv.domain.model.music.Music;
import gr.brid.castamuv.domain.model.music.MusicId;
import gr.brid.castamuv.domain.model.playlist.PlayList;
import gr.brid.castamuv.domain.model.playlist.PlayListId;
import gr.brid.castamuv.domain.model.user.Channel;
import gr.brid.castamuv.domain.model.user.ChannelId;
import gr.brid.castamuv.domain.model.user.Status;
import gr.brid.castamuv.domain.model.user.Stream;
import gr.brid.castamuv.domain.model.user.User;
import gr.brid.castamuv.domain.model.user.UserId;

import java.util.List;

public interface UserMusicboxGettingService {
	List<Channel> channels(UserId id);

	List<PlayList> playLists(UserId id);

	List<Music> musics(PlayListId id);

	List<Music> likes(UserId id);

	Status status(UserId id);

	List<Music> musics(ChannelId channelId);

	Stream next(ChannelId channelId);

	Music music(MusicId musicId);

	User user(UserId userId);
}
