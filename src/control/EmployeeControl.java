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

  private ObservableList<Employee> employees = FXCollections.observableArrayList();
  private TableView<Employee> table = new TableView<Employee>();
  private EmployeeDAO service = new EmployeeDAOImpl();
  private Formatters fmt = new Formatters();

  private StringProperty id = new SimpleStringProperty("");
  private BooleanProperty active = new SimpleBooleanProperty(true);
  private StringProperty email = new SimpleStringProperty("");
  private StringProperty username = new SimpleStringProperty("");
  private StringProperty fullname = new SimpleStringProperty("");
  private StringProperty role = new SimpleStringProperty("");
  private StringProperty telephoneNumber = new SimpleStringProperty("");
  private StringProperty bankDetails = new SimpleStringProperty("");
  private StringProperty specialty = new SimpleStringProperty("");
  private ObjectProperty<Date> birthDate = new SimpleObjectProperty<Date>();
  private ObjectProperty<Date> created = new SimpleObjectProperty<Date>();

  public void setEntity(Employee employee) {
    if (employee != null) {
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
      //fmt.timeDateToString
    }
  }

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
    employees.clear();
    employees.addAll(service.getAllEmployees());
  }

  public void findByEmail() {
    employees.clear();
    employees.addAll(service.findByField("email", getEmail()));
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

  public void generatedTable() {
    listAll();

    TableColumn<Employee, ObjectId> colActive = new TableColumn<>("Ativo");
    colActive.setCellValueFactory(
      new PropertyValueFactory<Employee, ObjectId>("active")
    );

    TableColumn<Employee, String> colEmail = new TableColumn<>("Email");
    colEmail.setCellValueFactory(
      new PropertyValueFactory<Employee, String>("email")
    );

    TableColumn<Employee, String> colUsername = new TableColumn<>("Username");
    colUsername.setCellValueFactory(
      new PropertyValueFactory<Employee, String>("username")
    );

    TableColumn<Employee, String> colFullname = new TableColumn<>(
      "Nome Completo"
    );
    colFullname.setCellValueFactory(
      new PropertyValueFactory<Employee, String>("fullname")
    );

    TableColumn<Employee, String> colRole = new TableColumn<>("Role");
    colRole.setCellValueFactory(
      new PropertyValueFactory<Employee, String>("role")
    );

    TableColumn<Employee, String> colTelephoneNumber = new TableColumn<>(
      "Telefone"
    );
    colTelephoneNumber.setCellValueFactory(
      new PropertyValueFactory<Employee, String>("telephoneNumber")
    );

    TableColumn<Employee, String> colBankDetails = new TableColumn<>(
      "Dados Bancarios"
    );
    colBankDetails.setCellValueFactory(
      new PropertyValueFactory<Employee, String>("bankDetails")
    );

    TableColumn<Employee, String> colSpecialty = new TableColumn<>(
      "Especialidade"
    );
    colSpecialty.setCellValueFactory(
      new PropertyValueFactory<Employee, String>("specialty")
    );

    TableColumn<Employee, Date> colBirthDate = new TableColumn<>(
      "Data de Nascimento"
    );
    colBirthDate.setCellValueFactory(
      new PropertyValueFactory<Employee, Date>("birthDate")
    );

    TableColumn<Employee, Date> colCreated = new TableColumn<>(
      "Data de Criação"
    );
    colCreated.setCellValueFactory(
      new PropertyValueFactory<Employee, Date>("created")
    );

    table
      .getColumns()
      .addAll(
        colActive,
        colEmail,
        colUsername,
        colFullname,
        colTelephoneNumber,
        colBankDetails,
        colSpecialty,
        colBirthDate,
        colCreated,
        colRole
      );

    table
      .getSelectionModel()
      .selectedItemProperty()
      .addListener(
        (obs, antigo, novo) -> {
          setEntity(novo);
        }
      );

    table.setLayoutY(305.0);
    table.setPrefHeight(469.0);
    table.setPrefWidth(1066.0);

    table.setItems(employees);
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

  public TableView<Employee> getTable() {
    return table;
  }

  public String getId() {
    return id.getValue();
  }

  public StringProperty idProperty() {
    return id;
  }

  public Boolean getActive() {
    return active.getValue();
  }

  public BooleanProperty activeProperty() {
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
