package control;

import javax.swing.JOptionPane;

import org.bson.types.ObjectId;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import entity.Employee;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SignUpControl {

	private EmployeeDAO service = new EmployeeDAOImpl();
	
	private ObjectProperty<ObjectId> id = new SimpleObjectProperty<ObjectId>(null);
	private StringProperty email = new SimpleStringProperty("");
	private StringProperty fullname = new SimpleStringProperty("");
	private StringProperty username = new SimpleStringProperty("");
	private StringProperty password = new SimpleStringProperty("");
	
	public void setEntity(Employee employee) {
		if (employee != null) {
			id.set(employee.getId());
			email.set(employee.getEmail());
			fullname.set(employee.getFullname());
			username.set(employee.getUsername());
			password.set(employee.getPassword());
		}
	}

	public Employee getEntity() {
		Employee employee = new Employee();
		employee.setId((ObjectId) id.get());
		employee.setEmail(email.get());
		employee.setFullname(fullname.get());		
		employee.setUsername(username.get());
		employee.setPassword(password.get());
		return employee;
	}
	
	public void signUp() {
		
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

	public void login() {
		// Abre tela de login
	}

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
	
	private void clearFields() {
		Employee employee = new Employee();
		employee.setId(null);
		id.set(null);
		email.set("");
		fullname.set("");
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
	
	public String getEmail() {
		return email.get();
	}

	public StringProperty emailProperty() {
		return email;
	}

	public String getFullname() {
		return fullname.get();
	}

	public StringProperty fullnameProperty() {
		return fullname;
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
