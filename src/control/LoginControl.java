package control;

import dao.impl.EmployeeDAOImpl;
import dao.interfaces.EmployeeDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utils.Security;

public class LoginControl {

  private EmployeeDAO service = new EmployeeDAOImpl();
  private Security security = new Security();

  private StringProperty username = new SimpleStringProperty("");
  private StringProperty password = new SimpleStringProperty("");

  public String login() {
    String username = usernameProperty().getValue();
    String password = passwordProperty().getValue();

    return service.findLoginData(username, password);
  }

  public String forgotPassword(String username, String password) {
    if (security.passwordVerification(password)) {
      return service.findAndUpdatePassword(username, password);
    } else {
      return "Senha não atende os requisitos minimos.";
    }
  }

  private void clearFields() {
    username.set("");
    password.set("");
  }

  public StringProperty usernameProperty() {
    return username;
  }

  public StringProperty passwordProperty() {
    return password;
  }
}
