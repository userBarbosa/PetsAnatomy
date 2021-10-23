package control;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;

public class SignUpControl {

  private EmployeeDAO service = new EmployeeDAOImpl();

  public void createUser(
    String email,
    String username,
    String fullname,
    String password
  ) {
    if (service.findToCreateUser(username, email)) {
      // doSomething
    }
  }

  public void passwordVerification(String password) {
    /* 
		rules:
		- at least 8 characters
		- must contain at least:
			1 letter;
			1 digit;
			1 symbol/special char.
		- up to 15 characters
		*/
    if (password.length() > 7 && password.length() < 16) {
      String specialPattern = "!#$&*+_-@";
      boolean valid[] = new boolean[3];
      for (char c : password.toCharArray()) {
        if (Character.isDigit(c)) valid[0] = true; else if (
          Character.isLetter(c)
        ) valid[1] = true;
        // else if valid special character valid [2] = true;
      }
    }
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
