package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {

	public String encryptPassword(String cipher) {
		if (cipher == null) {
			cipher = "12345";
		}
		StringBuilder builder = new StringBuilder();
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte MessageDigest[] = algorithm.digest(cipher.getBytes("UTF-8"));

			for (byte b : MessageDigest) {
				builder.append(String.format("%02X", b & 0xFF));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

}
