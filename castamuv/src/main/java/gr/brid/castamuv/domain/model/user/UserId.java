package gr.brid.castamuv.domain.model.user;

import gr.brid.castamuv.core.IdGenerator;
import gr.brid.castamuv.domain.shared.ValueObject;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class UserId implements ValueObject<UserId> {

	private static final long serialVersionUID = -711016002638171644L;

	private long id;

	public UserId() {
		this.id = IdGenerator.id();
	}

	public UserId(String id) {
		this.id = Long.valueOf(id);
	}

	@Override
	public String toString() {
		return Long.toString(this.id);
	}

	@Override
	public boolean sameValueOf(UserId object) {
		return this.id == object.getId();
	}

}
