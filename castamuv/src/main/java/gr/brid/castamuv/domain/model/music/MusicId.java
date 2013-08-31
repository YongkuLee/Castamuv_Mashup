package gr.brid.castamuv.domain.model.music;

import gr.brid.castamuv.core.IdGenerator;
import gr.brid.castamuv.domain.shared.ValueObject;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class MusicId implements ValueObject<MusicId> {

	private static final long serialVersionUID = -3153963775467133139L;

	private long id;

	public MusicId() {
		this.id = IdGenerator.id();
	}

	public MusicId(String id) {
		this.id = Long.parseLong(id);
	}

	@Override
	public boolean sameValueOf(MusicId object) {
		return this.id == object.getId();
	}
}
