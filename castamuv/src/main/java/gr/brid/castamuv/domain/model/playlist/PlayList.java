package gr.brid.castamuv.domain.model.playlist;

import gr.brid.castamuv.domain.model.music.Music;
import gr.brid.castamuv.domain.model.user.User;
import gr.brid.castamuv.domain.shared.EntityModel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PlayList implements EntityModel<PlayList> {

	@EmbeddedId
	private PlayListId id;

	@ManyToOne
	@JoinColumn
	private User editor;

	private String title;

	private String description;

	public PlayList(User user) {
		this.id = new PlayListId();
		this.musics = new ArrayList<Music>();
		this.editor = user;
		this.title = "";
		this.description = "";
	}

	@OneToMany
	private List<Music> musics;

	@Override
	public boolean sameEntityAs(PlayList model) {
		return this.id.sameValueOf(model.getId());
	}
}
