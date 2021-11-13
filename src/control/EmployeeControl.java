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
	  employee.setId(idProperty().getValue() == null ? new ObjectId() : new ObjectId(idProperty().getValue()));
	  employee.setActive(fmt.StringToBoolean(activeProperty().getValue()));
	  employee.setFullname(fullnameProperty().getValue());
	  employee.setTelephoneNumber(telephoneNumberProperty().getValue());
	  employee.setBankDetails(bankDetailsProperty().getValue());
	  employee.setSpecialty(specialtyProperty().getValue());
	  employee.setBirthDate(fmt.localToDate((LocalDate) birthDateProperty().getValue()));
	  return employee;
  }
  
  public void setEntity(Employee employee) {
	  id.setValue((String) employee.getId().toString());
	  active.setValue(fmt.BooleanToString(employee.getActive()));
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
  }

  public void updateById() {
    service.update(idProperty().getValue(), getEntity());
    this.listAll();
  }

  public void deleteById() {
    service.delete(idProperty().getValue());
    this.findByEmail();
  }

  public void listAll() {
	listEmployees.clear();
	listEmployees.addAll(service.getAllEmployees());
  }

  public void findByEmail() {
	listEmployees.clear();
	listEmployees.addAll(service.findByField("email", emailProperty().getValue()));
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
  
  public void readAll() {
	  List<Employee> allWorkers = service.getAllEmployees();
	  if (allWorkers != null) {
		  for (Employee each : allWorkers) {
			  System.out.println("All: " + each.getFullname());
		  }
	  } else {
		  System.err.println("There are no workers in database");
	  }
  }

  public void readByID(String id) {
    Employee query = service.findByID(id);
    System.out.println("ById: " + query);
  }

  public void readByDate(String field, Date dateGte, Date dateLte) {
    List<Employee> query = service.findByDate(field, dateGte, dateLte);

    if (query != null) {
      for (Employee each : query) {
        System.out.println("ByDate: " + each.getId());
      }
    } else {
      System.err.println("There are no workers in the specified dates");
    }
  }

  public void readByField(String field, String data) {
    List<Employee> query = service.findByField(field, data);
    System.out.println("ByField: " + query);
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
  /* tests structures; remove for final application:
  public static void main(String[] args) {
    EmployeeControl ec = new EmployeeControl();
    EmployeeDAO s1 = new EmployeeDAOImpl();

    List<Pair<String, String>> cb = s1.allEmployees();

    for (Pair<String, String> e : cb) {
      System.out.println(e.getKey() + " - " + e.getValue());
    }
    Employee marcos = new Employee(
	      "abc@email.com",
	      "Marcos",
	      "Marcos F R Barbosa",
	      "12345"
	    );
	    marcos.setId(new ObjectId());
	    ec.create(marcos);
	    ec.readByID(new ObjectId("6178893359bd6c23d3e59b27"));
	    ec.readByDate(
	      "created",
	      new Date().from(Instant.now().minusMillis((86400000))),
	      new Date().from(Instant.now().plusMillis(86400000))
	    );
	    ec.readByField("fullname", "Marcos Barbosa");
	    ec.update(new ObjectId("6178893359bd6c23d3e59b27"), marcos);
	    ec.delete(new ObjectId("6178893359bd6c23d3e59b27"));
	    ec.readAll(); 
  }*/
}
