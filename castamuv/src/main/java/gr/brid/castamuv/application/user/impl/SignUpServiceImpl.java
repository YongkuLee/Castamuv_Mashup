package gr.brid.castamuv.application.user.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.brid.castamuv.application.user.SignUpService;
import gr.brid.castamuv.domain.model.email.Email;
import gr.brid.castamuv.domain.model.email.EmailRepository;
import gr.brid.castamuv.domain.model.user.User;
import gr.brid.castamuv.domain.model.user.UserRepository;
import gr.brid.castamuv.infrastructure.security.Encriptor;

@Service
@Transactional
public class SignUpServiceImpl implements SignUpService {

	private EmailRepository emailRepository;

	private UserRepository userRepository;

	@Override
	public User signUp(String email, String password, String image) {

		Email emailObj = new Email(email);
		emailRepository.save(emailObj);

		User user = new User(emailObj, Encriptor.encript(password), image);
		userRepository.save(user);

		return user;
	}

	@Inject
	public void setEmailRepository(EmailRepository emailRepository) {
		this.emailRepository = emailRepository;
	}

	@Inject
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
