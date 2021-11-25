package control;

import dao.impl.EmployeeDAOImpl;
import dao.impl.ParametersDAOImpl;
import dao.interfaces.EmployeeDAO;
import dao.interfaces.ParametersDAO;
import entity.Employee;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import org.bson.types.ObjectId;

public class ConfigurationControl {

  private ObservableList<Employee> listEmployees = FXCollections.observableArrayList();
  private EmployeeDAO service = new EmployeeDAOImpl();
  private ParametersDAO serviceParameters = new ParametersDAOImpl();
  
  private StringProperty id = new SimpleStringProperty("");
  private StringProperty email = new SimpleStringProperty("");
  private StringProperty username = new SimpleStringProperty("");
  private StringProperty role = new SimpleStringProperty("");

  public Employee getEntity() {
    Employee employee = new Employee(
      emailProperty().getValue(),
      usernameProperty().getValue()
    );
    employee.setId(
      (idProperty().getValue() == "" || idProperty().getValue() == null)
        ? new ObjectId()
        : new ObjectId(idProperty().getValue())
    );
    employee.setRole(roleProperty().getValue());
    return employee;
  }

  public void setEntity(Employee employee) {
    id.set(employee.getId().toString());
    email.set(employee.getEmail());
    username.set(employee.getUsername());
    role.set(employee.getRole());
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
  }
  
  public void updateRole() {
	  service.updateField(
			  idProperty().getValue(),
			  "role",
			  roleProperty().getValue()
			  );
	  this.listAll();
	  this.clearFields();
  }

  public void resetPassword() {
	  service.updatePassword(idProperty().getValue(), "12345");
	  JOptionPane.showMessageDialog(
			  null,
			  "Senha resetada!",
			  "Sucesso",
			  JOptionPane.INFORMATION_MESSAGE
			  );
  }

  public void createPhrase(String phrase) {
	  serviceParameters.insertPhrase(phrase);
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
