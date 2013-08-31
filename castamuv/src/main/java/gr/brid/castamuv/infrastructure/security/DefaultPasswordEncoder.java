package gr.brid.castamuv.infrastructure.security;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;

public class DefaultPasswordEncoder implements PasswordEncoder {
	
	@Override
	public String encodePassword(String rawPass, Object salt)
			throws DataAccessException {
		return Encriptor.encript(rawPass);
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt)
			throws DataAccessException {
		return encPass.equals(Encriptor.encript(rawPass));
	}
	
}
