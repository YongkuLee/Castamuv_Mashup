package gr.brid.castamuv.application.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import gr.brid.castamuv.application.user.UserListingService;
import gr.brid.castamuv.domain.model.email.Email;
import gr.brid.castamuv.domain.model.user.User;
import gr.brid.castamuv.domain.model.user.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserListingServiceImpl implements UserListingService {

	private UserRepository userRepository;

	@Override
	public List<Email> allEmails() {

		List<Email> emails = new ArrayList<Email>();

		for (User user : userRepository.findAll()) {
			emails.addAll(user.getEmails());
		}

		return emails;
	}

	@Inject
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
