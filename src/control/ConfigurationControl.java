package control;

import org.bson.types.ObjectId;

import dao.impl.EmployeeDAOImpl;
import dao.interfaces.EmployeeDAO;
import entity.Employee;
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

  private StringProperty id = new SimpleStringProperty("");
  private StringProperty email = new SimpleStringProperty("");
  private StringProperty username = new SimpleStringProperty("");
  private StringProperty password = new SimpleStringProperty("");
  private StringProperty role = new SimpleStringProperty("");

  public void setEntity(Employee employee) {
    if (employee != null) {
      id.set(employee.getId().toString());
      email.set(employee.getEmail());
      username.set(employee.getUsername());
      password.set(employee.getPassword());
      role.set(employee.getRole());
    }
  }

  public Employee getEntity() {
    Employee employee = new Employee(emailProperty().getValue(), usernameProperty().getValue());
    employee.setId(new ObjectId(id.get()));
    employee.setPassword(password.get());
    employee.setRole(role.get());
    return employee;
  }

  public void updateById() {
    service.update(getId().toString(), getEntity());
    this.listAll();
  }

  private void listAll() {
    employees.clear();
    employees.addAll(service.getAllEmployees());
  }

  public void findByUsername() {
    employees.clear();
    employees.addAll(service.findByField("username", getUsername()));
  }

  public void clearFields() {
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
    colEmail.setCellValueFactory(
      new PropertyValueFactory<Employee, String>("email")
    );

    TableColumn<Employee, String> colUsername = new TableColumn<>("Username");
    colUsername.setCellValueFactory(
      new PropertyValueFactory<Employee, String>("username")
    );

    TableColumn<Employee, String> colPassword = new TableColumn<>("Senha");
    colPassword.setCellValueFactory(
      new PropertyValueFactory<Employee, String>("password")
    );

//    ObservableList<String> roles = FXCollections.observableArrayList("admin", "receptionist", "doctor");
//    cbRole.setItems(roles);
//    cbRole.setValue("Selecione");
	
	TableColumn<Employee, String> colRole = new TableColumn<>("Role");
	colRole.setCellValueFactory(new PropertyValueFactory<Employee, String>("role"));

    table.getColumns().addAll(colEmail, colUsername, colPassword, colRole);

    table
      .getSelectionModel()
      .selectedItemProperty()
      .addListener(
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

  public String getId() {
    return id.get();
  }

  public StringProperty idProperty() {
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
