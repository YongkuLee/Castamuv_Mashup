package gr.brid.castamuv.domain.model.link;

import javax.persistence.Embeddable;

import lombok.Data;

import gr.brid.castamuv.core.IdGenerator;
import gr.brid.castamuv.domain.shared.ValueObject;

@Embeddable
@Data
public class LinkId implements ValueObject<LinkId> {

	private static final long serialVersionUID = 2827215737892736951L;

	private long id;

	public LinkId() {
		this.id = IdGenerator.id();
	}

	@Override
	public boolean sameValueOf(LinkId object) {
		return this.id == object.getId();
	}
}
