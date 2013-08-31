package gr.brid.castamuv.domain.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

import gr.brid.castamuv.domain.model.music.Music;
import gr.brid.castamuv.domain.model.playlist.PlayList;
import gr.brid.castamuv.domain.shared.EntityModel;

@Entity
@Data
@NoArgsConstructor
public class Stream implements EntityModel<Stream> {

	@GeneratedValue
	@Id
	private long id;

	@ManyToOne
	@JoinColumn
	private Channel channel;

	@ManyToOne
	@JoinColumn
	private Music music;
	
	@ManyToOne
	@JoinColumn
	private PlayList playList;
	
	@ManyToOne
	@JoinColumn
	private User user;

	public Stream(Channel channel, Music music, PlayList playList, User user) {
		this.channel = channel;
		this.music = music;
		this.playList = playList;
		this.user = user;
	}

	@Override
	public boolean sameEntityAs(Stream model) {
		return this.id == model.getId();
	}

}
