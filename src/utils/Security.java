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

	public boolean passwordVerification(String password) {
    /* 
		rules:
		- at least 6 characters
		- up to 15 characters
		- must contain at least:
    1 uppercase letter;
    1 lowercase letter;
    1 digit;
    1 symbol/special char.
		 */
    if (password.length() > 5 && password.length() < 16) {
      String specialPattern = "!#$&*+_-@";

      boolean hasLowercase = false;
      boolean hasUppercase = false;
      boolean hasDigit = false;
      boolean hasSpecialCharacter = false;
      // boolean hasInvalidCharacter = false;

      for (char c : password.toCharArray()) {
        if (hasLowercase && hasUppercase && hasDigit && hasSpecialCharacter) break;

        if (Character.isLowerCase(c)) hasLowercase = true;
				else if (Character.isUpperCase(c)) hasUppercase = true;
				else if (Character.isDigit(c)) hasDigit = true;
				else if (specialPattern.contains(Character.toString(c))) hasSpecialCharacter = true;
				else return false; //has invalid characters
      }

      if (hasLowercase && hasUppercase && hasDigit && hasSpecialCharacter) {
        return true;
      }
    }
    return false; //the password does not attend one of the rules
  }

}
