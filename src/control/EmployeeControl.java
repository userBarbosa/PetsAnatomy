package control;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
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

public class EmployeeControl {

  private ObservableList<Employee> employees = FXCollections.observableArrayList();
  private TableView<Employee> table = new TableView<Employee>();
  private EmployeeDAO service = new EmployeeDAOImpl();

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
      id.set(employee.getId().toString());
      active.set(employee.getActive());
      email.set(employee.getEmail());
      username.set(employee.getUsername());
      fullname.set(employee.getFullname());
      role.set(employee.getRole());
      telephoneNumber.set(employee.getTelephoneNumber());
      bankDetails.set(employee.getBankDetails());
      specialty.set(employee.getSpecialty());
      birthDate.set(employee.getBirthDate());
      created.set(employee.getCreated());
    }
  }

  public Employee getEntity() {
    Employee employee = new Employee(
      email.toString(),
      username.toString(),
      fullname.toString()
    );

    employee.setId(new ObjectId(id.get()));
    employee.setActive(active.get());
    employee.setRole(role.get());
    employee.setTelephoneNumber(telephoneNumber.get());
    employee.setBankDetails(bankDetails.get());
    employee.setSpecialty(specialty.get());
    employee.setBirthDate((Date) birthDate.get());
    employee.setCreated((Date) created.get());
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
    id.set(null);
    active.set(true);
    email.set("");
    username.set("");
    fullname.set("");
    role.set("");
    telephoneNumber.set("");
    bankDetails.set("");
    specialty.set("");
    birthDate.set(null);
    created.set(null);
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

  public TableView<Employee> getTable() {
    return table;
  }

  public String getId() {
    return id.get();
  }

  public StringProperty idProperty() {
    return id;
  }

  public Boolean getActive() {
    return active.get();
  }

  public BooleanProperty activeProperty() {
    return active;
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

  public String getFullname() {
    return fullname.get();
  }

  public StringProperty fullnameProperty() {
    return fullname;
  }

  public String getRole() {
    return role.get();
  }

  public StringProperty roleProperty() {
    return role;
  }

  public String getTelephoneNumber() {
    return telephoneNumber.get();
  }

  public StringProperty telephoneNumberProperty() {
    return telephoneNumber;
  }

  public String getBankDetails() {
    return bankDetails.get();
  }

  public StringProperty bankDetailsProperty() {
    return bankDetails;
  }

  public String getSpecialty() {
    return specialty.get();
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
  }

  private List<String> workShift(
    int endingHourWorkShift,
    int startHourWorkShift,
    int appDuration
  ) {
    LocalTime workShift = LocalTime.of(startHourWorkShift, 0);
    List<String> workShiftList = new ArrayList<String>();

    while (workShift.plusMinutes(appDuration).getHour() < endingHourWorkShift) {
      workShift = workShift.plusMinutes(appDuration);
      workShiftList.add(workShift.toString());
    }
    return workShiftList;
  }

  private String dateToString(Date date) {
    // dd/MM/yyyy
    LocalDate fmt = LocalDate.ofInstant(
      date.toInstant(),
      ZoneId.systemDefault()
    );

    return (
      normalizeDateOutcome(fmt.getDayOfMonth()) +
      "/" +
      normalizeDateOutcome(fmt.getMonthValue()) +
      "/" +
      fmt.getYear()
    );
  }

  private String normalizeDateOutcome(int n) {
    if (n < 10) {
      return "0" + n;
    }
    return Integer.toString(n);
  }

  private String hourToString(Date date) {
    LocalTime fmt = LocalTime.ofInstant(
      date.toInstant(),
      ZoneId.systemDefault()
    );

    return (
      normalizeDateOutcome(fmt.getHour()) +
      ":" +
      normalizeDateOutcome(fmt.getMinute())
    );
  }
}
