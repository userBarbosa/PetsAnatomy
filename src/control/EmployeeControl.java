package control;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import dao.impl.EmployeeDAOImpl;
import dao.interfaces.EmployeeDAO;
import entity.Employee;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.Formatters;

public class EmployeeControl {

  private ObservableList<Employee> listEmployees = FXCollections.observableArrayList();
  private EmployeeDAO service = new EmployeeDAOImpl();
  private Formatters fmt = new Formatters();

  private StringProperty id = new SimpleStringProperty("");
  private StringProperty active = new SimpleStringProperty("");
  private StringProperty email = new SimpleStringProperty("");
  private StringProperty username = new SimpleStringProperty("");
  private StringProperty fullname = new SimpleStringProperty("");
  private StringProperty telephoneNumber = new SimpleStringProperty("");
  private StringProperty bankDetails = new SimpleStringProperty("");
  private StringProperty specialty = new SimpleStringProperty("");
  private ObjectProperty birthDate = new SimpleObjectProperty();

  public Employee getEntity() {
	  Employee employee = new Employee(emailProperty().getValue(), usernameProperty().getValue());
	  employee.setId((idProperty().getValue() == "" || idProperty().getValue() == null) ? new ObjectId() : new ObjectId(idProperty().getValue()));
	  employee.setActive(fmt.activeStringToBoolean(activeProperty().getValue()));
	  employee.setFullname(fullnameProperty().getValue());
	  employee.setTelephoneNumber(telephoneNumberProperty().getValue());
	  employee.setBankDetails(bankDetailsProperty().getValue());
	  employee.setSpecialty(specialtyProperty().getValue());
	  employee.setBirthDate(fmt.localToDate((LocalDate) birthDateProperty().getValue()));
	  return employee;
  }
  
  public void setEntity(Employee employee) {
	  id.setValue((String) employee.getId().toString());
	  active.setValue(fmt.activeBooleanToString(employee.getActive()));
	  email.setValue(employee.getEmail());
	  username.setValue(employee.getUsername());
	  fullname.setValue(employee.getFullname());
	  telephoneNumber.setValue(employee.getTelephoneNumber());
	  bankDetails.setValue(employee.getBankDetails());
	  specialty.setValue(employee.getSpecialty());
	  birthDate.setValue(fmt.DateToLocal(employee.getBirthDate()));
  }
  
  public void create() {
    service.insert(getEntity());
    this.listAll();
    this.clearFields();
  }

  public void updateById() {
    service.update(idProperty().getValue(), getEntity());
    this.listAll();
    this.clearFields();
  }

  public void deleteById() {
    service.delete(idProperty().getValue());
    this.findByField();
    this.clearFields();
  }

  public void listAll() {
	listEmployees.clear();
	listEmployees.addAll(service.getAllEmployees());
  }
  
  public void findByField() {
	  listEmployees.clear();
	  listEmployees.addAll(service.findByField("fullname", fullnameProperty().getValue()));
	  this.clearFields();
  }

  public void clearFields() {
    id.setValue("");
    active.setValue("");
    email.setValue("");
    username.setValue("");
    fullname.setValue("");
    telephoneNumber.setValue("");
    bankDetails.setValue("");
    specialty.setValue("");
    birthDate.setValue(null);
    this.listAll();
  }

  public ObservableList<Employee> getListEmployees() {
	  return listEmployees;
  }

  public StringProperty idProperty() {
	  return id;
  }

  public StringProperty activeProperty() {
	  return active;
  }

  public StringProperty emailProperty() {
	  return email;
  }
  
  public StringProperty usernameProperty() {
	  return username;
  }

  public StringProperty fullnameProperty() {
	  return fullname;
  }

  public StringProperty telephoneNumberProperty() {
	  return telephoneNumber;
  }

  public StringProperty bankDetailsProperty() {
	  return bankDetails;
  }

  public StringProperty specialtyProperty() {
	  return specialty;
  }

  public ObjectProperty birthDateProperty() {
	  return birthDate;
  }

}
