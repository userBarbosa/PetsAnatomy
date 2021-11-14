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

  private ObservableList<Employee> listEmployees = FXCollections.observableArrayList();
  private EmployeeDAO service = new EmployeeDAOImpl();

  private StringProperty id = new SimpleStringProperty("");
  private StringProperty email = new SimpleStringProperty("");
  private StringProperty username = new SimpleStringProperty("");
  private StringProperty role = new SimpleStringProperty("");

  public Employee getEntity() {
	  Employee employee = new Employee(emailProperty().getValue(), usernameProperty().getValue());
	  employee.setId((idProperty().getValue() == "" || idProperty().getValue() == null) ? new ObjectId() : new ObjectId(idProperty().getValue()));
	  employee.setRole(roleProperty().getValue());
	  return employee;
  }
  
  public void setEntity(Employee employee) {
      id.set(employee.getId().toString());
      email.set(employee.getEmail());
      username.set(employee.getUsername());
      role.set(employee.getRole());
  }

  public void updateById() {
    service.update(idProperty().getValue(), getEntity());
    this.listAll();
    this.clearFields();
  }

  public void listAll() {
	  listEmployees.clear();
	  listEmployees.addAll(service.getAllEmployees());
  }

  public void clearFields() {
    id.set("");
    email.set("");
    username.set("");
    role.set("");
    this.listAll();
  }
  
  public ObservableList<Employee> getListEmployees() {
	  return listEmployees;
  }

  public StringProperty idProperty() {
    return id;
  }

  public StringProperty emailProperty() {
    return email;
  }
  
  public StringProperty usernameProperty() {
    return username;
  }

  public StringProperty roleProperty() {
    return role;
  }
  
}
