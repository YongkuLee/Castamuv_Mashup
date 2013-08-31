package gr.brid.castamuv.infrastructure.security;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

public class Encriptor {

	private static Logger logger = Logger.getLogger(Encriptor.class);

	private static final String ALGORITHM = "SHA1";

	public static String encript(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}

		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance(ALGORITHM);
			byte[] inputByte = input.getBytes();
			md.update(inputByte);

			byte[] digest = md.digest();
			for (int i = 0, j = digest.length; i < j; i++) {
				result += Integer.toHexString(digest[i]).toUpperCase();
			}
		} catch (Exception e) {
			logger.error(ALGORITHM + "암호화 에러." + e.getMessage(), e);
		}

		return result;
	}
}
