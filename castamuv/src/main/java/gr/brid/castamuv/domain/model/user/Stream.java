package gr.brid.castamuv.domain.model.user;

import gr.brid.castamuv.domain.model.music.MusicId;
import gr.brid.castamuv.domain.model.playlist.PlayListId;
import gr.brid.castamuv.domain.shared.EntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Stream implements EntityModel<Stream> {

	@GeneratedValue
	@Id
	@Getter
	private long id;

	@Column(name = "channel_id")
	private long channelId;

	@Column(name = "music_id")
	private long musicId;

	@Column(name = "play_list_id")
	private long playListId;

	@Column(name = "user_id")
	private long userId;

	@Override
	public boolean sameEntityAs(Stream model) {
		return this.id == model.getId();
	}

	public Stream(ChannelId channelId, MusicId musicId, PlayListId playListId,
			UserId userId) {
		super();
		this.channelId = channelId.getId();
		this.musicId = musicId.getId();
		this.playListId = playListId.getId();
		this.userId = userId.getId();
	}

	public ChannelId getChannelId() {
		return new ChannelId(this.channelId);
	}

	public MusicId getMusicId() {
		return new MusicId(this.musicId);
	}

	public PlayListId getPlayListId() {
		return new PlayListId(this.playListId);
	}

	public UserId getUserId() {
		return new UserId(this.userId);
	}

}
