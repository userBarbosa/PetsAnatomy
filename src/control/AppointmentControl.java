package control;

import dao.impl.AppointmentDAOImpl;
import dao.interfaces.AppointmentDAO;
import entity.Appointment;
import java.time.LocalDate;
import java.util.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.types.ObjectId;
import utils.Formatters;

public class AppointmentControl {

  private ObservableList<Appointment> listAppointments = FXCollections.observableArrayList();
  private AppointmentDAO service = new AppointmentDAOImpl();
  private Formatters fmt = new Formatters();

  private StringProperty id = new SimpleStringProperty("");
  private StringProperty patientId = new SimpleStringProperty("");
  private StringProperty ownerId = new SimpleStringProperty("");
  private StringProperty employeeId = new SimpleStringProperty("");
  private StringProperty obs = new SimpleStringProperty("");
  private IntegerProperty state = new SimpleIntegerProperty();
  private IntegerProperty financialState = new SimpleIntegerProperty();
  private DoubleProperty value = new SimpleDoubleProperty();
  private ObjectProperty date = new SimpleObjectProperty();
  private ObjectProperty time = new SimpleObjectProperty();
  
  private ObjectProperty dateGte = new SimpleObjectProperty();
  private ObjectProperty dateLte = new SimpleObjectProperty();

  public Appointment getEntity() {
    ObjectId employeeId = new ObjectId(employeeIdProperty().getValue());
    ObjectId patientId = new ObjectId(patientIdProperty().getValue());
    ObjectId ownerId = new ObjectId(ownerIdProperty().getValue());
    Date date = fmt.stringToTimeDate(
      dateProperty().getValue().toString(),
      timeProperty().getValue().toString()
    );
    double value = valueProperty().getValue();

    Appointment appointment = new Appointment(
      employeeId,
      patientId,
      ownerId,
      date,
      value
    );
    appointment.setId(
      (idProperty().getValue() == "" || idProperty().getValue() == null)
        ? new ObjectId()
        : new ObjectId(idProperty().getValue())
    );
    appointment.setObs(obsProperty().getValue());
    appointment.setState(stateProperty().getValue());
    appointment.setFinancialState(financialStateProperty().getValue());
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
    time.setValue(fmt.dateToLocal(appointment.getDate()));
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
    this.listAll();
  }

  public void listAll() {
    listAppointments.clear();
    listAppointments.addAll(service.getAllAppointments());
  }
  
  public void findByField() {
	  listAppointments.clear();
	  listAppointments.addAll(service.findByField("patientId", patientIdProperty().getValue()));
	}

  public void findByDate() {
    listAppointments.clear();
    listAppointments.addAll(service.findByDate("date", (Date) dateGteProperty().getValue(), (Date) dateLteProperty().getValue()));
    this.clearFields();
  }

  public void clearFields() {
    id.set("");
    patientId.set("");
    ownerId.set("");
    employeeId.set("");
    obs.set("");
    state.set(0);
    value.set(0);
    financialState.set(0);
    date.set(null);
    time.set(null);
    this.listAll();
  }
  
  public String dateToString(Date value) {
	  return fmt.dateToString(value);
  }

  public ObservableList<Appointment> getListAppointments() {
    return listAppointments;
  }

  public StringProperty idProperty() {
    return id;
  }

  public StringProperty patientIdProperty() {
    return patientId;
  }

  public StringProperty ownerIdProperty() {
    return ownerId;
  }

  public StringProperty employeeIdProperty() {
    return employeeId;
  }

  public StringProperty obsProperty() {
    return obs;
  }

  public IntegerProperty stateProperty() {
    return state;
  }

  public IntegerProperty financialStateProperty() {
    return financialState;
  }

  public DoubleProperty valueProperty() {
    return value;
  }

  public ObjectProperty dateProperty() {
    return date;
  }

  public ObjectProperty timeProperty() {
    return time;
  }
  
  public ObjectProperty dateGteProperty() {
	    return dateGte;
  }
  
  public ObjectProperty dateLteProperty() {
	    return dateLte;
  }

}
