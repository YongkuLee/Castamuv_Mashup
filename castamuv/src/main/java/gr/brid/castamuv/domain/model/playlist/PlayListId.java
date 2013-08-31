package gr.brid.castamuv.domain.model.playlist;

import javax.persistence.Embeddable;

import lombok.Data;

import gr.brid.castamuv.core.IdGenerator;
import gr.brid.castamuv.domain.shared.ValueObject;

@Embeddable
@Data
public class PlayListId implements ValueObject<PlayListId> {

	private static final long serialVersionUID = 6504205865490089699L;

	private long id;

	public PlayListId() {
		this.id = IdGenerator.id();
	}

	public PlayListId(String id) {
		this.id = Long.parseLong(id);
	}

	@Override
	public boolean sameValueOf(PlayListId object) {
		return this.id == object.getId();
	}

}
