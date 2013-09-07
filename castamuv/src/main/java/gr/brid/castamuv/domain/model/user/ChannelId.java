package gr.brid.castamuv.domain.model.user;

import javax.persistence.Embeddable;

import lombok.Data;
import gr.brid.castamuv.core.IdGenerator;
import gr.brid.castamuv.domain.shared.ValueObject;

@Data
@Embeddable
public class ChannelId implements ValueObject<ChannelId> {
	private static final long serialVersionUID = -6749581503346423142L;
	
	private long id;

	public ChannelId() {
		this.id = IdGenerator.id();
	}

	public ChannelId(String id) {
		this.id = Long.parseLong(id);
	}
	
	public ChannelId(long id) {
		this.id = id;
	}

	@Override
	public boolean sameValueOf(ChannelId object) {
		return this.id == object.getId();
	}

}
