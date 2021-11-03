package control;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import entity.Employee;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.swing.JOptionPane;
import org.bson.types.ObjectId;

public class LoginControl {

	private EmployeeDAO service = new EmployeeDAOImpl();

	private ObjectProperty<ObjectId> id = new SimpleObjectProperty<ObjectId>(null);
	private StringProperty username = new SimpleStringProperty("");
	private StringProperty password = new SimpleStringProperty("");

	public void setEntity(Employee employee) {
		if (employee != null) {
			id.set(employee.getId());
			username.set(employee.getUsername());
			password.set(employee.getPassword());
		}
	}

	public Employee getEntity() {
		Employee employee = new Employee();
		employee.setId((ObjectId) id.get());
		employee.setUsername(username.get());
		employee.setPassword(password.get());
		return employee;
	}

	public void login(String username, String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if (service.findLoginData(username, password)) {
			// SUCCESS
		} else {
			// FAILED
		}
	}

	public void login() {

		clearFields();

		if (role.equals("")) {
			// Abre a Dashboard sem ações
			JOptionPane.showMessageDialog(null, "Entre em contato com o Administrador!",
					"Login", JOptionPane.INFORMATION_MESSAGE);

		}
		else if (role.equals("admin")) {
			JOptionPane.showMessageDialog(null, "Seja bem vindo Administrador!",
					"Login", JOptionPane.INFORMATION_MESSAGE);
			// Abre a Dashboard com ações para Administrador
		}

		else if (role.equals("receptionist")) {
			JOptionPane.showMessageDialog(null, "Seja bem vindo colaborador(a)!",
					"Login", JOptionPane.INFORMATION_MESSAGE);
			// Abre a Dashboard com ações para Colaborador
		}

		else if (role.equals("doctor")) {
			JOptionPane.showMessageDialog(null, "Seja bem vindo Doutor(a)!",
					"Login", JOptionPane.INFORMATION_MESSAGE);
			// Abre a Dashboard com ações para Doutor
		}

		else {
			JOptionPane.showMessageDialog(null, "Erro ao entrar",
					"Erro no Login", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void signUp() {
		// Abre tela de signup
	}

	private void clearFields() {
		Employee employee = new Employee();
		employee.setId(null);
		id.set(null);
		username.set("");
		password.set("");
		employee.setRole(""); 
	}

	public ObjectId getId() {
		return (ObjectId) id.get();
	}

	public ObjectProperty idProperty() {
		return id;
	}

	public String getUsername() {
		return username.get();
	}

	public StringProperty usernameProperty() {
		return username;
	}

	public String getPassword() {
		return password.get();
	}

	public StringProperty passwordProperty() {
		return password;
	}

}
