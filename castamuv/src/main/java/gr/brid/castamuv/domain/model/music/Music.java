package gr.brid.castamuv.domain.model.music;

import java.util.List;

import gr.brid.castamuv.domain.model.link.Link;
import gr.brid.castamuv.domain.shared.EntityModel;

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
public class Music implements EntityModel<Music> {

	@EmbeddedId
	private MusicId id;

	private String title;

	private String description;

	@ManyToOne
	@JoinColumn
	private Link link;
	
	@OneToMany
	private List<Like> likes;

	public Music(Link link) {
		this.id = new MusicId();
		this.link = link;
	}

	@Override
	public boolean sameEntityAs(Music model) {
		return this.id.sameValueOf(model.getId());
	}

}
