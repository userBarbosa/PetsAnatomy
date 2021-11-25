package control;

import java.time.LocalDate;
import java.util.Date;

import javax.swing.JOptionPane;

import org.bson.types.ObjectId;

import dao.impl.EmployeeDAOImpl;
import dao.interfaces.EmployeeDAO;
import entity.Employee;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
  private StringProperty role = new SimpleStringProperty("");
  private ObjectProperty birthDate = new SimpleObjectProperty();

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
    employee.setActive(fmt.activeStringToBoolean(activeProperty().getValue()));
    employee.setFullname(fullnameProperty().getValue());
    employee.setTelephoneNumber(telephoneNumberProperty().getValue());
    employee.setBankDetails(bankDetailsProperty().getValue());
    employee.setSpecialty(specialtyProperty().getValue());
    employee.setBirthDate(
      localToDate((LocalDate) birthDateProperty().getValue())
    );
    employee.setRole(roleProperty().getValue());
    return employee;
  }

  public void setEntity(Employee employee) {
    id.set((String) employee.getId().toString());
    active.set(fmt.activeBooleanToString(employee.getActive()));
    email.set(employee.getEmail());
    username.set(employee.getUsername());
    fullname.set(employee.getFullname());
    telephoneNumber.set(employee.getTelephoneNumber());
    bankDetails.set(employee.getBankDetails());
    specialty.set(employee.getSpecialty());
    role.set(employee.getRole());
    birthDate.set(dateToLocal((Date) employee.getBirthDate()));
  }

  public void clearFields() {
    id.set("");
    active.set("");
    email.set("");
    username.set("");
    fullname.set("");
    telephoneNumber.set("");
    bankDetails.set("");
    specialty.set("");
    birthDate.set(null);
    role.set("");
  }

  public void create() {
    if (
      service.findToCreateUser(
        usernameProperty().getValue(),
        emailProperty().getValue()
      )
    ) {
      service.insert(getEntity());
      this.clearFields();
    } else {
      JOptionPane.showMessageDialog(
      null,
      "Usuario criado!",
      "Sucesso",
      JOptionPane.INFORMATION_MESSAGE
    );
    }
    this.listAll();
  }

  public void updateById() {
    service.update(idProperty().getValue(), getEntity());
    this.listAll();
    this.clearFields();
  }
  
  public void deleteById() {
    service.delete(idProperty().getValue());
    this.listAll();
    this.clearFields();
  }

  public void listAll() {
    listEmployees.clear();
    listEmployees.addAll(service.getAllEmployees());
  }

  public void findByField() {
    listEmployees.clear();
    listEmployees.addAll(
      service.findByField("fullname", fullnameProperty().getValue())
    );
  }

  public String dateToString(Date value) {
	  if (value != null) {
      return fmt.dateToString(value);
    }
    return null;
  }
  
  public LocalDate dateToLocal(Date value) {
    if (value != null) {
      return fmt.dateToLocal(value);
    }
    return null;
  }
  public Date localToDate(LocalDate value) {
    if (value != null) {
      return fmt.localToDate(value);
    }
    return null; 
  }

  public String activeBooleanToString(Boolean value) {
	return fmt.activeBooleanToString(value);
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

  public StringProperty roleProperty() {
    return role;
  }

  public ObjectProperty birthDateProperty() {
    return birthDate;
  }
}
  