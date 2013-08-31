package gr.brid.castamuv.infrastructure.security;

import gr.brid.castamuv.domain.model.user.UserRepository;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultUserService implements UserDetailsService {

	private UserRepository userRepository;

	@Inject
	public DefaultUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException, DataAccessException {
		gr.brid.castamuv.domain.model.user.User user = userRepository
				.findByEmail(email);

		User detail = new User(user.getId().toString(), user.getPassword(),
				user.isEnable(), true, true, true, user.getAuthorities());

		return detail;
	}

}
