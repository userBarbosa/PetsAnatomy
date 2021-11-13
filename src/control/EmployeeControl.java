package control;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import entity.Employee;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
  private StringProperty role = new SimpleStringProperty("");
  private StringProperty telephoneNumber = new SimpleStringProperty("");
  private StringProperty bankDetails = new SimpleStringProperty("");
  private StringProperty specialty = new SimpleStringProperty("");
  private ObjectProperty<Date> birthDate = new SimpleObjectProperty<Date>();
  private ObjectProperty<Date> created = new SimpleObjectProperty<Date>();

  public Employee getEntity() {
	  Employee employee = new Employee(getEmail(), getUsername());
	  employee.setFullname(getFullname());
	  employee.setId(new ObjectId(getId()));
	  employee.setActive(getActive());
	  employee.setRole(getRole());
	  employee.setTelephoneNumber(getTelephoneNumber());
	  employee.setBankDetails(getBankDetails());
	  employee.setSpecialty(getSpecialty());
	  employee.setBirthDate((Date) getBirthDate());
	  employee.setCreated((Date) getCreated());
	  /* employee.setBirthDate(fmt.stringToDate(birthDate.getValue()));
	    employee.setCreated(
	      fmt.stringToTimeDate(createdProperty().getValue(), "00:00")
	    ); */
	  return employee;
  }
  
  public void setEntity(Employee employee) {
	  id.setValue(employee.getId().toString());
	  active.setValue(employee.getActive());
	  email.setValue(employee.getEmail());
	  username.setValue(employee.getUsername());
	  fullname.setValue(employee.getFullname());
	  role.setValue(employee.getRole());
	  telephoneNumber.setValue(employee.getTelephoneNumber());
	  bankDetails.setValue(employee.getBankDetails());
	  specialty.setValue(employee.getSpecialty());
	  birthDate.setValue(employee.getBirthDate());
	  created.setValue(employee.getCreated());
  }
  
  public void create() {
    service.insert(getEntity());
    this.listAll();
  }

  public void updateById() {
    service.update(getId(), getEntity());
    this.listAll();
  }

  public void deleteById() {
    service.delete(getId());
    this.findByEmail();
  }

  private void listAll() {
	listEmployees.clear();
	listEmployees.addAll(service.getAllEmployees());
  }

  public void findByEmail() {
	listEmployees.clear();
	listEmployees.addAll(service.findByField("email", getEmail()));
  }

  public void clearFields() {
    id.setValue(null);
    active.setValue(false);
    email.setValue("");
    username.setValue("");
    fullname.setValue("");
    role.setValue("");
    telephoneNumber.setValue("");
    bankDetails.setValue("");
    specialty.setValue("");
    birthDate.setValue(null);
    created.setValue(null);
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

  public String getId() {
    return id.getValue();
  }

  public StringProperty idProperty() {
    return id;
  }

  public String getActive() {
    return active.getValue();
  }

  public StringProperty activeProperty() {
    return active;
  }

  public String getEmail() {
    return email.getValue();
  }

  public StringProperty emailProperty() {
    return email;
  }

  public String getUsername() {
    return username.getValue();
  }

  public StringProperty usernameProperty() {
    return username;
  }

  public String getFullname() {
    return fullname.getValue();
  }

  public StringProperty fullnameProperty() {
    return fullname;
  }

  public String getRole() {
    return role.getValue();
  }

  public StringProperty roleProperty() {
    return role;
  }

  public String getTelephoneNumber() {
    return telephoneNumber.getValue();
  }

  public StringProperty telephoneNumberProperty() {
    return telephoneNumber;
  }

  public String getBankDetails() {
    return bankDetails.getValue();
  }

  public StringProperty bankDetailsProperty() {
    return bankDetails;
  }

  public String getSpecialty() {
    return specialty.getValue();
  }

  public StringProperty specialtyProperty() {
    return specialty;
  }

  public Object getBirthDate() {
    return birthDate.get();
  }

  public ObjectProperty birthDateProperty() {
    return birthDate;
  }

  public Object getCreated() {
    return created.get();
  }

  public ObjectProperty createdProperty() {
    return created;
  } //entender o que ele esta esperando

  public ObservableList<Employee> getListView() {
	  return listEmployees;
  }

  
  /* public String getBirthDate() {
    return birthDate.getValue();
  }

  public StringProperty birthDateProperty() {
    return birthDate;
  }

  public String getCreated() {
    return created.getValue();
  }

  public StringProperty createdProperty() {
    return created;
  } */
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
