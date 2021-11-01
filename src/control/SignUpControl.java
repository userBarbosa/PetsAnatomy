package control;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import entity.Employee;

public class SignUpControl {

	private EmployeeDAO service = new EmployeeDAOImpl();

	public void createUser(
			String email,
			String username,
			String fullname,
			String password
			) {
		if (service.findToCreateUser(username, email)) {
			Employee user = new Employee(email, username, fullname, password);
			service.insert(user);
		}
	}

	public boolean passwordVerification(String password) {
		/* 
		rules:
		- at least 8 characters
		- must contain at least:
    1 uppercase letter;
    1 lowercase letter;
    1 digit;
    1 symbol/special char.
		- up to 15 characters
		 */
		if (password.length() > 7 && password.length() < 16) {
			String specialPattern = "!#$&*+_-@";

			boolean hasLowercase = false;
			boolean hasUppercase = false;
			boolean hasDigit = false;
			boolean hasSpecialCharacter = false;
			// boolean hasInvalidCharacter = false;

			for (char c : password.toCharArray()) {
				if (
						hasLowercase && hasUppercase && hasDigit && hasSpecialCharacter
						) break;

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

	public boolean passwordConfirmation(
			String password,
			String passwordConfirmation
			) {
		if (password.hashCode() == passwordConfirmation.hashCode()) {
			return true;
		}
		return false;
	}
	
}
