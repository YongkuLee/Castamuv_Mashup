package gr.brid.castamuv.domain.model.link;

import gr.brid.castamuv.domain.shared.EntityModel;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Link implements EntityModel<Link> {

	@EmbeddedId
	private LinkId id;

	private LinkType type;

	private String value;

	private String thumbnail;

	public Link(LinkType type, String value) {
		this.id = new LinkId();
		this.type = type;
		this.value = value;
	}

	@Override
	public boolean sameEntityAs(Link model) {
		return this.id.sameValueOf(model.getId());
	}

}
