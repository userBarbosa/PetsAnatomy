package control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.types.ObjectId;

import dao.AppointmentDAO;
import dao.AppointmentDAOImpl;
import entity.Appointment;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AppointmentControl {

  private ObservableList<Appointment> listAppointments = FXCollections.observableArrayList();
  private AppointmentDAO service = new AppointmentDAOImpl();

  private StringProperty id = new SimpleStringProperty("");
  private StringProperty patientId = new SimpleStringProperty("");
  private StringProperty ownerId = new SimpleStringProperty("");
  private StringProperty employeeId = new SimpleStringProperty("");
  private StringProperty obs = new SimpleStringProperty("");
  private IntegerProperty state = new SimpleIntegerProperty(0);
  private IntegerProperty financialState = new SimpleIntegerProperty(0);
  private DoubleProperty value = new SimpleDoubleProperty(0);
  private ObjectProperty date = new SimpleObjectProperty();
  private ObjectProperty time = new SimpleObjectProperty();
  
  String cbOpState [] = {"agendado", "encerrado", "cancelada"};
  String cbOpFinancialState [] = {"pago", "parcialmente pago", "n�o pago", "cancelado"};

  public Appointment getEntity() {
	  Appointment appointment = new Appointment();
	  appointment.setId(new ObjectId(id.get()));
	  appointment.setPatientId(new ObjectId(patientId.get()));
	  appointment.setOwnerId(new ObjectId(ownerId.get()));
	  appointment.setEmployeeId(new ObjectId(employeeId.get()));
	  appointment.setObs(obs.get());
	  appointment.setState(state.get());
	  appointment.setFinancialState(financialState.get());
	  appointment.setValue(value.get());
	  appointment.setDate((Date) date.get());
	  appointment.setDate((Date) time.get());
	  return appointment;
  }

  public void setEntity(Appointment appointment) {
      id.setValue(appointment.getId().toString());
      patientId.setValue(appointment.getPatientId().toString());
      ownerId.setValue(appointment.getOwnerId().toString());
      employeeId.setValue(appointment.getEmployeeId().toString());
      obs.setValue(appointment.getObs());
      state.setValue(appointment.getFinancialState());
      value.setValue(appointment.getValue());
      date.setValue(appointment.getDate());
      time.setValue(appointment.getDate());
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
	  listAppointments.clear();
		ObservableList allToArray = FXCollections.observableArrayList(
			service.returnAll().toArray()
			);
		listAppointments.addAll(allToArray);
		
  }


  public void findByDate() {
	  listAppointments.clear();
    // appointments.addAll(service.findByDate(getDate()));
    // appointments.addAll(service.findByDate("created", new Date(), new Date()));
  }

  public void clearFields() {
    Appointment appointment = getEntity();
    appointment.setId(null);
    id.set(null);
    patientId.set(null);
    ownerId.set(null);
    employeeId.set(null);
    obs.set("");
    state.set(0);
    value.set(0.0);
    date.set(null);
    time.set(null);
    this.listAll();
  }
  
  public String getId() {
    return id.getValue();
  }

  public StringProperty idProperty() {
    return id;
  }

  public String getPatientId() {
    return patientId.get();
  }

  public StringProperty patientIdProperty() {
    return patientId;
  }

  public String getOwnerId() {
    return ownerId.get();
  }

  public StringProperty ownerIdProperty() {
    return ownerId;
  }

  public String getEmployeeId() {
    return employeeId.get();
  }

  public StringProperty employeeIdProperty() {
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

public ObservableList<Appointment> getListView() {
	return listAppointments;
}
  
}
