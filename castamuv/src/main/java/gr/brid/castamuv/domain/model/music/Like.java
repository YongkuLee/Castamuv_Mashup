package gr.brid.castamuv.domain.model.music;

import gr.brid.castamuv.domain.model.user.User;
import gr.brid.castamuv.domain.shared.EntityModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Like implements EntityModel<Like> {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	@JoinColumn
	private Music music;

	@ManyToOne
	@JoinColumn
	private User user;

	public Like(Music music, User user) {
		this.music = music;
		this.user = user;
	}

	@Override
	public boolean sameEntityAs(Like model) {
		return this.music.sameEntityAs(model.getMusic())
				&& this.user.sameEntityAs(model.getUser());
	}

}
