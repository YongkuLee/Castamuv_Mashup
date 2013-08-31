package gr.brid.castamuv.application.user;

import gr.brid.castamuv.domain.model.email.Email;

import java.util.List;

public interface UserListingService {
	List<Email> allEmails();
}
