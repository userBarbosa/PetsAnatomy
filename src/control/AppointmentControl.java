package control;

import dao.impl.AppointmentDAOImpl;
import dao.impl.EmployeeDAOImpl;
import dao.impl.OwnerDAOImpl;
import dao.impl.PatientDAOImpl;
import dao.interfaces.AppointmentDAO;
import dao.interfaces.EmployeeDAO;
import dao.interfaces.OwnerDAO;
import dao.interfaces.PatientDAO;
import entity.Appointment;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import javax.swing.JOptionPane;
import org.bson.types.ObjectId;
import utils.Formatters;

public class AppointmentControl {

  private ObservableList<Appointment> listAppointments = FXCollections.observableArrayList();
  private AppointmentDAO service = new AppointmentDAOImpl();
  private PatientDAO servicePatient = new PatientDAOImpl();
  private OwnerDAO serviceOwner = new OwnerDAOImpl();
  private EmployeeDAO serviceEmployee = new EmployeeDAOImpl();
  private Formatters fmt = new Formatters();

  private StringProperty id = new SimpleStringProperty("");
  private StringProperty patientId = new SimpleStringProperty("");
  private StringProperty ownerId = new SimpleStringProperty("");
  private StringProperty employeeId = new SimpleStringProperty("");
  private StringProperty obs = new SimpleStringProperty("");
  private StringProperty state = new SimpleStringProperty("");
  private StringProperty financialState = new SimpleStringProperty("");
  private DoubleProperty value = new SimpleDoubleProperty();
  private ObjectProperty date = new SimpleObjectProperty();
  private StringProperty time = new SimpleStringProperty("");

  public Appointment getEntity() {
    double value = valueProperty().getValue();

    Appointment appointment = new Appointment(
      new ObjectId(getEmployeeIdByName(employeeIdProperty().getValue())),
      new ObjectId(getPatientIdByName(patientIdProperty().getValue())),
      new ObjectId(getOwnerIdByName(ownerIdProperty().getValue())),
      tryToGetDate(
        (LocalDate) dateProperty().getValue(),
        timeProperty().getValue().toString()
      ),
      value
    );
    appointment.setId(tryToGetId(idProperty().getValue()));
    appointment.setObs(obsProperty().getValue());
    appointment.setState(fmt.stateStringToInteger(stateProperty().getValue()));
    appointment.setFinancialState(
      fmt.financialStateStringToInteger(financialStateProperty().getValue())
    );
    return appointment;
  }

  public void setEntity(Appointment appointment) {
    id.set(appointment.getId().toString());
    patientId.set(getPatientNameById(appointment.getPatientId().toString()));
    ownerId.set(getOwnerNameById(appointment.getOwnerId().toString()));
    employeeId.set(getEmployeeNameById(appointment.getEmployeeId().toString()));
    obs.set(appointment.getObs());
    financialState.set(
      fmt.financialStateIntegerToString(appointment.getFinancialState())
    );
    state.set(fmt.stateIntegerToString(appointment.getState()));
    value.set(appointment.getValue());
    date.set(fmt.dateToLocal(appointment.getDate()));
    time.set(fmt.hourToString(appointment.getDate()));
  }

  public void create() {
    Appointment app = getEntity();

    if (
      !service.findScheduleAppointment(
        "employeeId",
        app.getEmployeeId().toString(),
        app.getDate()
      ) &&
      !service.findScheduleAppointment(
        "patientId",
        app.getPatientId().toString(),
        app.getDate()
      )
    ) {
      service.insert(app);
      servicePatient.updateLastVisit(
        app.getPatientId().toString(),
        app.getDate()
      );
      this.listAll();
      this.clearFields();
    } else {
      JOptionPane.showMessageDialog(
        null,
        "Médico ou pacientes ocupados nessa data e horario",
        "Error",
        JOptionPane.ERROR_MESSAGE
      );
    }
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
    listAppointments.addAll(
      service.findManyById(
        "patientId",
        getPatientIdByName(patientIdProperty().getValue())
      )
    );
  }

  public void findByDate(LocalDate dateGte, LocalDate dateLt) {
    listAppointments.clear();
    listAppointments.addAll(
      service.findByDate(
        "date",
        fmt.localToDate(dateGte),
        fmt.localToDate(dateLt.plusDays(1))
      )
    );
    this.clearFields();
  }

  public void clearFields() {
    id.set("");
    patientId.set("");
    ownerId.set("");
    employeeId.set("");
    obs.set("");
    state.set("");
    value.set(0);
    financialState.set("");
    date.set(null);
    time.set("");
  }

  public ObservableList<String> getAllPatientIdAndNames() {
    List<Pair<String, String>> patients = servicePatient.getAllIdAndNames();
    ObservableList<String> patientsName = FXCollections.observableArrayList();
    for (Pair<String, String> name : patients) {
      patientsName.addAll(name.getValue());
    }
    return patientsName;
  }

  public ObservableList<String> getAllOwnerIdAndNames() {
    List<Pair<String, String>> owners = serviceOwner.getAllIdAndNames();
    ObservableList<String> ownersName = FXCollections.observableArrayList();
    for (Pair<String, String> name : owners) {
      ownersName.addAll(name.getValue());
    }
    return ownersName;
  }

  public ObservableList<String> getAllEmployeeIdAndNames() {
    List<Pair<String, String>> employees = serviceEmployee.getAllIdAndNames();
    ObservableList<String> employeesName = FXCollections.observableArrayList();
    for (Pair<String, String> name : employees) {
      employeesName.addAll(name.getValue());
    }
    return employeesName;
  }

  public String getPatientNameById(String value) {
    List<Pair<String, String>> patients = servicePatient.getAllIdAndNames();
    for (Pair<String, String> name : patients) {
      if (name.getKey().equals(value)) {
        return name.getValue();
      }
    }
    return null;
  }

  private String getPatientIdByName(String value) {
    List<Pair<String, String>> patients = servicePatient.getAllIdAndNames();
    for (Pair<String, String> name : patients) {
      if (name.getValue().equals(value)) {
        return name.getKey();
      }
    }
    return null;
  }

  public String getOwnerNameById(String value) {
    List<Pair<String, String>> owners = serviceOwner.getAllIdAndNames();
    for (Pair<String, String> name : owners) {
      if (name.getKey().equals(value)) {
        return name.getValue();
      }
    }
    return null;
  }

  private String getOwnerIdByName(String value) {
    List<Pair<String, String>> owners = serviceOwner.getAllIdAndNames();
    for (Pair<String, String> name : owners) {
      if (name.getValue().equals(value)) {
        return name.getKey();
      }
    }
    return null;
  }

  public String getEmployeeNameById(String value) {
    List<Pair<String, String>> employees = serviceEmployee.getAllIdAndNames();
    for (Pair<String, String> name : employees) {
      if (name.getKey().equals(value)) {
        return name.getValue();
      }
    }
    return null;
  }

  private String getEmployeeIdByName(String value) {
    List<Pair<String, String>> employees = serviceEmployee.getAllIdAndNames();
    for (Pair<String, String> name : employees) {
      if (name.getValue().equals(value)) {
        return name.getKey();
      }
    }
    return null;
  }

  public String dateToString(Date value) {
    return fmt.dateToString(value);
  }

  public String hourToString(Date value) {
    return fmt.hourToString(value);
  }

  public String stateIntegerToString(Integer value) {
    return fmt.stateIntegerToString(value);
  }

  public String financialStateIntegerToString(Integer value) {
    return fmt.financialStateIntegerToString(value);
  }

  public ObservableList<Appointment> getListAppointments() {
    return listAppointments;
  }
  
  private ObjectId tryToGetId(String property) {
	  return (property.isBlank() || property == null)
			  ? new ObjectId()
					  : new ObjectId(property);
  }

  private Date tryToGetDate(LocalDate dateProperty, String timeProperty) {
	  return fmt.stringToTimeDate(fmt.localToString(dateProperty), timeProperty);
  }

  public List<String> getPatientByOwnerName(String newer) {
	  return servicePatient.getPetsByOwner(getOwnerIdByName(newer));
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

  public StringProperty stateProperty() {
    return state;
  }

  public StringProperty financialStateProperty() {
    return financialState;
  }

  public DoubleProperty valueProperty() {
    return value;
  }

  public ObjectProperty dateProperty() {
    return date;
  }

  public StringProperty timeProperty() {
    return time;
  }
  
}
