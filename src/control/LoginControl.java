package control;

import dao.impl.EmployeeDAOImpl;
import dao.interfaces.EmployeeDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.swing.JOptionPane;
import utils.Security;

public class LoginControl {

  private EmployeeDAO service = new EmployeeDAOImpl();
  private Security security = new Security();

  private StringProperty username = new SimpleStringProperty("");
  private StringProperty password = new SimpleStringProperty("");

  public void login() {
    String username = usernameProperty().getValue();
    String password = passwordProperty().getValue();

    identification(service.findLoginData(username, password));
  }

  public void forgotPassword(String username, String password) {
    if (security.passwordVerification(password)) {
      identification(service.findAndUpdatePassword(username, password));
    } else {
      JOptionPane.showMessageDialog(
        null,
        "Senha não atende os requisitos minimos.",
        "Login",
        JOptionPane.INFORMATION_MESSAGE
      );
    }
  }

  public void identification(String role) {
    clearFields();

    switch (role) {
      case "admin":
        JOptionPane.showMessageDialog(
          null,
          "Seja bem vindo Administrador!",
          "Login",
          JOptionPane.INFORMATION_MESSAGE
        );
        // Abre a Dashboard com ações para Administrador
        break;
      case "receptionist":
        JOptionPane.showMessageDialog(
          null,
          "Seja bem vindo colaborador(a)!",
          "Login",
          JOptionPane.INFORMATION_MESSAGE
        );
        // Abre a Dashboard com ações para Colaborador
        break;
      case "doctor":
        JOptionPane.showMessageDialog(
          null,
          "Seja bem vindo Doutor(a)!",
          "Login",
          JOptionPane.INFORMATION_MESSAGE
        );
        // Abre a Dashboard com ações para Doutor
        break;
      case "":
        JOptionPane.showMessageDialog(
          null,
          "Entre em contato com o Administrador!",
          "Login",
          JOptionPane.INFORMATION_MESSAGE
        );
        // Abre a Dashboard sem ações
        break;
      case "400 - Bad Request":
      case "401 - Unauthorized":
        JOptionPane.showMessageDialog(
          null,
          "Your data was not found, contact your system administrator",
          role,
          JOptionPane.INFORMATION_MESSAGE
        );
        break;
      default:
        JOptionPane.showMessageDialog(
          null,
          "Erro ao entrar",
          "Erro no Login",
          JOptionPane.INFORMATION_MESSAGE
        );
        break;
    }
  }

  public void signUp() {
    // Abre tela de signup
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
