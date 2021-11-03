package control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import entity.Employee;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConfigurationControl {
	
	private ObservableList<Employee> employees = FXCollections.observableArrayList();
	private TableView<Employee> table = new TableView<Employee>();
	private EmployeeDAO service = new EmployeeDAOImpl();

	private ObjectProperty<ObjectId> id = new SimpleObjectProperty<ObjectId>(null);
	private StringProperty email = new SimpleStringProperty("");
	private StringProperty username = new SimpleStringProperty("");
	private StringProperty password = new SimpleStringProperty("");
	private StringProperty role = new SimpleStringProperty("");

	public void setEntity(Employee employee) {
		if (employee != null) {
			id.set(employee.getId());
			email.set(employee.getEmail());
			username.set(employee.getUsername());
			password.set(employee.getPassword());
			role.set(employee.getRole());
		}
	}

	public Employee getEntity() {
		Employee employee = new Employee();
		employee.setId((ObjectId) id.get());
		employee.setEmail(email.get());
		employee.setUsername(username.get());
		employee.setPassword(password.get());
		employee.setRole(role.get());
		return employee;
	}

	public void updateById() {
		service.update(getId(), getEntity());
		this.listAll();
	}

	private void listAll() {
		employees.clear();
		employees.addAll(service.returnAll());
	}

	public void findByUsername() {
		employees.clear();
		employees.addAll(service.findByField("username", getUsername()));
	}

	public void clearFields() {
		Employee employee = new Employee();
		employee.setId(null);
		id.set(null);
		email.set("");
		username.set("");
		password.set("");
		role.set("");
		this.listAll();
	}

	public void generatedTable() {
		listAll();
		TableColumn<Employee, String> colEmail = new TableColumn<>("Email");
		colEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));

		TableColumn<Employee, String> colUsername = new TableColumn<>("Username");
		colUsername.setCellValueFactory(new PropertyValueFactory<Employee, String>("username"));

		TableColumn<Employee, String> colPassword = new TableColumn<>("Senha");
		colPassword.setCellValueFactory(new PropertyValueFactory<Employee, String>("password"));

		TableColumn<Employee, String> colRole = new TableColumn<>("Role");
		colRole.setCellValueFactory(new PropertyValueFactory<Employee, String>("role"));
		
		table.getColumns().addAll(
				colEmail, 
				colUsername, 
				colPassword,
				colRole
				);

		table.getSelectionModel().selectedItemProperty().addListener(
				(obs, antigo, novo) -> {
					setEntity(novo);
				}
				);
		
        table.setLayoutY(200.0);
        table.setPrefHeight(574.0);
        table.setPrefWidth(1066.0);
        
		table.setItems(employees);
	}

	public TableView<Employee> getTable() {
		return table;
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

	public String getRole() {
		return role.get();
	}

	public StringProperty roleProperty() {
		return role;
	}

}
