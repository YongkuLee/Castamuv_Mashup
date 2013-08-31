package gr.brid.castamuv.domain.model.user;

import gr.brid.castamuv.domain.model.email.Email;
import gr.brid.castamuv.domain.model.playlist.PlayList;
import gr.brid.castamuv.domain.shared.DefaultValues;
import gr.brid.castamuv.domain.shared.EntityModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

@Entity
@Data
@NoArgsConstructor
public class User implements EntityModel<User> {

	@EmbeddedId
	private UserId id;

	private String username;

	private String image;

	private String password;

	private boolean enable;

	@OneToMany
	private Set<Email> emails;

	private String authorities;

	private long coin;

	@OneToMany
	private List<PlayList> playLists;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Channel> channels;

	private Status status;

	public Set<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> set = new HashSet<GrantedAuthority>();

		for (String role : this.authorities.split(",")) {
			GrantedAuthority auth = new GrantedAuthorityImpl(role);
			set.add(auth);
		}

		return set;
	}

	public void addAuthority(String role) {
		Set<GrantedAuthority> set = getAuthorities();
		set.add(new GrantedAuthorityImpl(role));
		setAuthorities(set);
	}

	public void removeAuthority(String role) {
		Set<GrantedAuthority> set = getAuthorities();
		set.remove(new GrantedAuthorityImpl(role));
		setAuthorities(set);
	}

	public void setAuthorities(Set<GrantedAuthority> set) {
		String authorities = "";
		for (GrantedAuthority authority : set) {
			authorities += authority.getAuthority();
			authorities += ",";
		}
		this.authorities = authorities.substring(0, authorities.length() - 1);
	}

	public User(Email email, String password, String image) {
		this.id = new UserId();
		this.password = password;
		this.authorities = DefaultValues.DEFAULT_AUTHORITY;
		this.enable = true;
		this.emails = new HashSet<Email>();
		this.emails.add(email);
		this.coin = 0;
		this.playLists = new ArrayList<PlayList>();
		this.channels = new ArrayList<Channel>();
		this.status = new Status();
		this.image = image;
	}

	@Override
	public boolean sameEntityAs(User model) {
		return this.id.sameValueOf(model.getId());
	}

}
