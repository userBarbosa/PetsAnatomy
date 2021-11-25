package control;

import javax.swing.JOptionPane;

import dao.impl.EmployeeDAOImpl;
import dao.interfaces.EmployeeDAO;
import entity.Employee;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utils.Security;

public class SignUpControl {

  private EmployeeDAO service = new EmployeeDAOImpl();
  private Security security = new Security();

  private StringProperty email = new SimpleStringProperty("");
  private StringProperty fullname = new SimpleStringProperty("");
  private StringProperty username = new SimpleStringProperty("");
  private StringProperty password = new SimpleStringProperty("");

  public void signUp() {
		String password = passwordProperty().getValue();
		if (security.passwordVerification(password)) {
			createUser(
				fullnameProperty().getValue(),
				usernameProperty().getValue(),
				emailProperty().getValue(),
				password
			);
			JOptionPane.showMessageDialog(
					null,
					"Registration successful!",
					"200 - OK",
					JOptionPane.INFORMATION_MESSAGE
					);
		} else {
      JOptionPane.showMessageDialog(
          null,
          "The password does not attend one of the rules or has invalid caracters.",
          "400 - Bad Request",
          JOptionPane.INFORMATION_MESSAGE
        );
		}
  }

  public void createUser(
    String fullname,
    String username,
    String email,
    String password
  ) {
    if (service.findToCreateUser(username, email)) {
      Employee user = new Employee(email, username);
      user.setPassword(password);
      user.setFullname(fullname);
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
