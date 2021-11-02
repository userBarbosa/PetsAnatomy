package control;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public class EmployeeControl {

	private ObservableList<Employee> employees = FXCollections.observableArrayList();
	private TableView<Employee> table = new TableView<Employee>();
	private EmployeeDAO service = new EmployeeDAOImpl();

	private ObjectProperty<ObjectId> id = new SimpleObjectProperty<ObjectId>(null);
	private BooleanProperty active = new SimpleBooleanProperty(true);
	private StringProperty email = new SimpleStringProperty("");
	private StringProperty username = new SimpleStringProperty("");
	private StringProperty fullname = new SimpleStringProperty("");
	private StringProperty role = new SimpleStringProperty("");
	private StringProperty telephoneNumber = new SimpleStringProperty("");
	private StringProperty bankDetails = new SimpleStringProperty("");
	private ListProperty<String> specialty = new SimpleListProperty<String>();
	private ObjectProperty<Date> birthDate = new SimpleObjectProperty<Date>();
	private ObjectProperty<Date> created = new SimpleObjectProperty<Date>();

	public void setEntity(Employee employee) {
		if (employee != null) {
			id.set(employee.getId());
			active.set(employee.getActive());
			email.set(employee.getEmail());
			username.set(employee.getUsername());
			fullname.set(employee.getFullname());
			role.set(employee.getRole());
			telephoneNumber.set(employee.getTelephoneNumber());
			bankDetails.set(employee.getBankDetails());
			specialty.setValue(toObservableList(employee.getSpecialty()));
			birthDate.set(employee.getBirthDate());
			created.set(employee.getCreated());
		}
	}

	ObservableList<String> toObservableList(List<String> l) {
		ObservableList<String> obsList = FXCollections.observableArrayList(l);
		return obsList;
	}

	String state [] = {"agendado", "encerrado", "cancelada"};
	String financialState [] = {"pago", "parcialmente pago", "não pago", "cancelado"};

	public Employee getEntity() {
		Employee employee = new Employee();
		employee.setId((ObjectId) id.get());
		employee.setActive(active.get());
		employee.setEmail(email.get());
		employee.setUsername(username.get());
		employee.setFullname(fullname.get());
		employee.setRole(role.get());
		employee.setTelephoneNumber(telephoneNumber.get());
		employee.setBankDetails(bankDetails.get());
		
		Iterator<String> i = specialty.iterator();
		while (i.hasNext()){
			employee.addSpecialty(i.next());
		}

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
		employees.addAll(service.returnAll());
	}

	public void findByEmail() {
		employees.clear();
		employees.addAll(service.findByField("email", getEmail()));
	}

	public void clearFields() {
		Employee employee = new Employee();
		employee.setId(null);
		id.set(0);
		active.set(true);
		email.set("");
		username.set("");
		fullname.set("");
		role.set("");
		telephoneNumber.set("");
		bankDetails.set("");
		// specialty.set("Selecione");
		birthDate.set(null);
		created.set(null);
		this.listAll();
	}

	public void generatedTable() {
		listAll();
		TableColumn<Employee, ObjectId> colId = new TableColumn<>("Id");
		colId.setCellValueFactory(new PropertyValueFactory<Employee, ObjectId>("id"));

		TableColumn<Employee, ObjectId> colActive = new TableColumn<>("Ativo");
		colActive.setCellValueFactory(new PropertyValueFactory<Employee, ObjectId>("active"));

		TableColumn<Employee, String> colEmail = new TableColumn<>("Email");
		colEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));

		TableColumn<Employee, String> colUsername = new TableColumn<>("Username");
		colUsername.setCellValueFactory(new PropertyValueFactory<Employee, String>("username"));

		TableColumn<Employee, String> colFullname = new TableColumn<>("Nome Completo");
		colFullname.setCellValueFactory(new PropertyValueFactory<Employee, String>("fullname"));

		TableColumn<Employee, String> colRole = new TableColumn<>("Role");
		colRole.setCellValueFactory(new PropertyValueFactory<Employee, String>("role"));

		TableColumn<Employee, String> colTelephoneNumber = new TableColumn<>("Telefone");
		colTelephoneNumber.setCellValueFactory(new PropertyValueFactory<Employee, String>("telephoneNumber"));
		
		TableColumn<Employee, String> colBankDetails = new TableColumn<>("Dados Bancarios");
		colBankDetails.setCellValueFactory(new PropertyValueFactory<Employee, String>("bankDetails"));
		
		TableColumn<Employee, String> colSpecialty = new TableColumn<>("Especialidade");
		colSpecialty.setCellValueFactory(new PropertyValueFactory<Employee, String>("specialty"));
		
		TableColumn<Employee, String> colBirthDate = new TableColumn<>("Data de Nascimento");
		colBirthDate.setCellValueFactory( (employeeProp) -> {
			Date n = employeeProp.getValue().getBirthDate();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String strData = dateFormat.format(n);
			return new ReadOnlyStringWrapper(strData);
		} );

		TableColumn<Employee, String> colCreated = new TableColumn<>("Data de Cria��o");
		colCreated.setCellValueFactory( (employeeProp) -> {
			Date n = employeeProp.getValue().getCreated();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String strData = dateFormat.format(n);
			return new ReadOnlyStringWrapper(strData);
		} );

		table.getColumns().addAll(colId, colActive, colEmail, colUsername, colFullname, colTelephoneNumber, colBankDetails, colSpecialty, colBirthDate, colCreated, colRole);

		table.getSelectionModel().selectedItemProperty().addListener(
				(obs, antigo, novo) -> {
					setEntity(novo);
				}
				);

		table.setItems(employees);
	}

	public void readAll() {
		List<Employee> allWorkers = service.returnAll();
		if (allWorkers != null) {
			for (Employee each : allWorkers) {
				System.out.println("All: " + each.getFullname());
			}
		} else {
			System.err.println("There are no workers in database");
		}
	}

	public void readByID(ObjectId id) {
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
	  }  */

	public TableView<Employee> getTable() {
		return table;
	}

	public ObjectId getId() {
		return (ObjectId) id.get();
	}

	public ObjectProperty idProperty() {
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

	public List getSpecialty() {
		return specialty;
	}

	public ListProperty specialtyProperty() {
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

}
