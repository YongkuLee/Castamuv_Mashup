package gr.brid.castamuv.application.user;

import gr.brid.castamuv.domain.model.user.User;

public interface SignUpService {

	User signUp(String email, String password, String image);

}
