package gr.brid.castamuv.domain.model.email;

import gr.brid.castamuv.domain.model.user.User;
import gr.brid.castamuv.domain.shared.EntityModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(of = "email")
@NoArgsConstructor
public class Email implements EntityModel<Email> {

	@Id
	private String email;

	private boolean verified;

	@ManyToOne
	@JoinColumn
	private User user;

	public Email(String email) {
		this.email = email;
		this.verified = false;
	}

	@Override
	public boolean sameEntityAs(Email model) {
		return this.email.equals(model.getEmail());
	}

}
