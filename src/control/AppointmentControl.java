package control;

import dao.AppointmentDAO;
import dao.AppointmentDAOImpl;
import entity.Appointment;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.types.ObjectId;

public class AppointmentControl {

  private ObservableList<Appointment> appointments = FXCollections.observableArrayList();
  private TableView<Appointment> table = new TableView<Appointment>();
  private AppointmentDAO service = new AppointmentDAOImpl();

  private ObjectProperty id = new SimpleObjectProperty("");
  private ObjectProperty patientId = new SimpleObjectProperty("");
  private ObjectProperty ownerId = new SimpleObjectProperty("");
  private ObjectProperty employeeId = new SimpleObjectProperty("");
  private StringProperty obs = new SimpleStringProperty("");
  private IntegerProperty state = new SimpleIntegerProperty(0);
  private IntegerProperty financialState = new SimpleIntegerProperty(0);
  private DoubleProperty value = new SimpleDoubleProperty(0);
  private ObjectProperty date = new SimpleObjectProperty();
  private ObjectProperty time = new SimpleObjectProperty();

  public void setEntity(Appointment appointment) {
    if (appointment != null) {
      id.set(appointment.getId());
      patientId.set(appointment.getPatientId());
      ownerId.set(appointment.getOwnerId());
      employeeId.set(appointment.getEmployeeId());
      obs.set(appointment.getObs());
      state.set(appointment.getFinancialState());
      value.set(appointment.getValue());
      date.set(appointment.getDate());
      time.set(appointment.getDate());
    }
  }

  public Appointment getEntity() {
    Appointment appointment = new Appointment();
    appointment.setId((ObjectId) id.get());
    appointment.setPatientId((ObjectId) patientId.get());
    appointment.setOwnerId((ObjectId) ownerId.get());
    appointment.setEmployeeId((ObjectId) employeeId.get());
    appointment.setObs(obs.get());
    appointment.setState(state.get());
    appointment.setFinancialState(financialState.get());
    appointment.setValue(value.get());
    appointment.setDate((Date) date.get());
    appointment.setDate((Date) time.get());
    return appointment;
  }

  public void create() {
    service.insert(getEntity());
    this.listAll();
  }

  public void updateById() {
    service.update(getId(), getEntity());
    this.findByDate();
  }

  public void deleteById() {
    service.delete(getId());
    this.listAll();
  }

  public void listAll() {
    appointments.clear();
		ObservableList allToArray = FXCollections.observableArrayList(
			service.returnAll().toArray()
			);
		appointments.addAll(allToArray);
		
  }


  public void findByDate() {
    appointments.clear();
    appointments.addAll(service.findByDate(getDate()));
    // appointments.addAll(service.findByDate("created", new Date(), new Date()));
  }

  public void clearFields() {
    Appointment appointment = getEntity();
    appointment.setId(null);
    id.set(0);
    patientId.set(0);
    ownerId.set(0);
    employeeId.set(0);
    obs.set("");
    state.set(0);
    value.set(0.0);
    date.set(null);
    time.set(null);
    this.listAll();
  }

  public void generatedTable() {
    listAll();
    TableColumn<Appointment, ObjectId> colId = new TableColumn<>("Id");
    colId.setCellValueFactory(
      new PropertyValueFactory<Appointment, ObjectId>("id")
    );

    TableColumn<Appointment, String> colDate = new TableColumn<>("Date");
    colDate.setCellValueFactory(
      appointmentProp -> {
        Date n = appointmentProp.getValue().getDate();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strData = dateFormat.format(n);
        return new ReadOnlyStringWrapper(strData);
      }
    );

    TableColumn<Appointment, String> colTime = new TableColumn<>("Hor�rio");
    colTime.setCellValueFactory(
      appointmentProp -> {
        Date n = appointmentProp.getValue().getDate();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String strData = dateFormat.format(n);
        return new ReadOnlyStringWrapper(strData);
      }
    );

    TableColumn<Appointment, ObjectId> colPatient = new TableColumn<>(
      "Paciente"
    );
    colPatient.setCellValueFactory(
      new PropertyValueFactory<Appointment, ObjectId>("patientId")
    );

    

    TableColumn<Appointment, String> colOwner = new TableColumn<>("Dono");
    colOwner.setCellValueFactory(
      new PropertyValueFactory<Appointment, String>("ownerId")
    );

    TableColumn<Appointment, ObjectId> colEmployee = new TableColumn<>(
      "M�dico"
    );
    colEmployee.setCellValueFactory(
      new PropertyValueFactory<Appointment, ObjectId>("employeeId")
    );

    TableColumn<Appointment, Integer> colState = new TableColumn<>("Status");
    colState.setCellValueFactory(
      new PropertyValueFactory<Appointment, Integer>("state")
    );

    TableColumn<Appointment, Integer> colFinancialState = new TableColumn<>(
      "Pagamento"
    );
    colFinancialState.setCellValueFactory(
      new PropertyValueFactory<Appointment, Integer>("financialState")
    );

    TableColumn<Appointment, String> colValue = new TableColumn<>("Valor");
    colValue.setCellValueFactory(
      new PropertyValueFactory<Appointment, String>("value")
    );

    TableColumn<Appointment, String> colObs = new TableColumn<>("Observa��o");
    colObs.setCellValueFactory(
      new PropertyValueFactory<Appointment, String>("obs")
    );

    table
      .getColumns()
      .addAll(
        colId,
        colDate,
        colTime,
        colPatient,
        colOwner,
        colEmployee,
        colState,
        colFinancialState,
        colValue,
        colObs
      );

    table
      .getSelectionModel()
      .selectedItemProperty()
      .addListener(
        (obs, antigo, novo) -> {
          setEntity(novo);
        }
      );

    table.setItems(appointments);
  }

  public TableView<Appointment> getTable() {
    return table;
  }

  public ObjectId getId() {
    return (ObjectId) id.get();
  }

  public ObjectProperty<ObjectId> idProperty() {
    return id;
  }

  public ObjectId getPatientId() {
    return (ObjectId) patientId.get();
  }

  public ObjectProperty patientIdProperty() {
    return patientId;
  }

  public ObjectId getOwnerId() {
    return (ObjectId) ownerId.get();
  }

  public ObjectProperty ownerIdProperty() {
    return ownerId;
  }

  public ObjectId getEmployeeId() {
    return (ObjectId) employeeId.get();
  }

  public ObjectProperty employeeIdProperty() {
    return employeeId;
  }

  public String getObs() {
    return obs.get();
  }

  public StringProperty obsProperty() {
    return obs;
  }

  public Integer getState() {
    return state.get();
  }

  public IntegerProperty stateProperty() {
    return state;
  }

  public Integer getFinancialState() {
    return financialState.get();
  }

  public IntegerProperty financialStateProperty() {
    return financialState;
  }

  public Double getValue() {
    return value.get();
  }

  public DoubleProperty valueProperty() {
    return value;
  }

  public Object getDate() {
    return date.get();
  }

  public ObjectProperty dateProperty() {
    return date;
  }

  public Object getTime() {
    return time.get();
  }

  public ObjectProperty timeProperty() {
    return time;
  }
}
