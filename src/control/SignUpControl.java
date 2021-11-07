package control;

import javax.swing.JOptionPane;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import entity.Employee;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SignUpControl {

  private EmployeeDAO service = new EmployeeDAOImpl();

  private StringProperty email = new SimpleStringProperty("");
  private StringProperty fullname = new SimpleStringProperty("");
  private StringProperty username = new SimpleStringProperty("");
  private StringProperty password = new SimpleStringProperty("");

  public void signUp() {
		String password = passwordProperty().getValue();
		if (passwordVerification(password)) {
			createUser(
				fullnameProperty().getValue(),
				usernameProperty().getValue(),
				emailProperty().getValue(),
				password
			);
		} else {
			System.err.println("the password does not attend one of the rules or has invalid caracters.");
      //-fx-text-box-border: red; -fx-focus-color: red;
		}
		
  }

  public void login() {
    // Abre tela de login
  }

  public void createUser(
    String fullname,
    String username,
    String email,
    String password
  ) {
    if (service.findToCreateUser(username, email)) {
      Employee user = new Employee(email, username, fullname);
      user.setPassword(password);
      service.insert(user);
    } else {
			JOptionPane.showMessageDialog(
          null,
          "User or e-mail already exists, please try another.",
          "409 - Conflict",
          JOptionPane.INFORMATION_MESSAGE
        );
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

        if (Character.isLowerCase(c)) hasLowercase = true; else if (
          Character.isUpperCase(c)
        ) hasUppercase = true; else if (Character.isDigit(c)) hasDigit =
          true; else if (
          specialPattern.contains(Character.toString(c))
        ) hasSpecialCharacter = true; else return false; //has invalid characters
      }

      if (hasLowercase && hasUppercase && hasDigit && hasSpecialCharacter) {
        return true;
      }
    }
    return false; //the password does not attend one of the rules
  }

  private void clearFields() {
    email.set("");
    fullname.set("");
    username.set("");
    password.set("");
  }

  public StringProperty emailProperty() {
    return email;
  }

  public StringProperty fullnameProperty() {
    return fullname;
  }

  public StringProperty usernameProperty() {
    return username;
  }

  public StringProperty passwordProperty() {
    return password;
  }
}
