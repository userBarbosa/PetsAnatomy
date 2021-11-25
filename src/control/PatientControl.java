package control;

import dao.impl.OwnerDAOImpl;
import dao.impl.PatientDAOImpl;
import dao.interfaces.OwnerDAO;
import dao.interfaces.PatientDAO;
import entity.Patient;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import org.bson.types.ObjectId;
import utils.Formatters;

public class PatientControl {

  private ObservableList<Patient> listPatients = FXCollections.observableArrayList();
  private PatientDAO service = new PatientDAOImpl();
  private OwnerDAO serviceOwner = new OwnerDAOImpl();
  private Formatters fmt = new Formatters();

  private StringProperty id = new SimpleStringProperty("");
  private StringProperty ownerId = new SimpleStringProperty("");
  private StringProperty name = new SimpleStringProperty("");
  private StringProperty species = new SimpleStringProperty("");
  private StringProperty family = new SimpleStringProperty("");
  private StringProperty bloodtype = new SimpleStringProperty("");
  private StringProperty obs = new SimpleStringProperty("");
  private ObjectProperty birthdate = new SimpleObjectProperty();
  private StringProperty lastVisit = new SimpleStringProperty();
  private StringProperty treatment = new SimpleStringProperty("");

  public Patient getEntity() {
    Patient patient = new Patient(
      nameProperty().getValue(),
      new ObjectId(getIdByName(ownerIdProperty().getValue())),
      speciesProperty().getValue(),
      familyProperty().getValue()
    );
    patient.setId(tryToGetId(idProperty().getValue()));
    patient.setBloodtype(bloodtypeProperty().getValue());
    patient.setObs(obsProperty().getValue());
    patient.setBirthdate(
      fmt.localToDate((LocalDate) birthdateProperty().getValue())
    );
    patient.setLastVisit(tryToGetLastVisit(lastVisitProperty().getValue()));
    patient.setTreatment(
      fmt.treatmentStringToBoolean(treatmentProperty().getValue())
    );
    return patient;
  }

  public void setEntity(Patient patient) {
    id.set(patient.getId().toString());
    ownerId.set(getNameById(patient.getOwnerId().toString()));
    name.set(patient.getName());
    species.set(patient.getSpecies());
    family.set(patient.getFamily());
    bloodtype.set(patient.getBloodtype());
    obs.set(patient.getObs());
    birthdate.set(fmt.dateToLocal(patient.getBirthdate()));
    lastVisit.set(fmt.timeDateToString(patient.getLastVisit()));
    treatment.set(fmt.treatmentBooleanToString(patient.getTreatment()));
  }

  public void create() {
    Patient p = getEntity();
    service.insert(p, p.getOwnerId().toString());
    serviceOwner.updatePatientList(
      p.getOwnerId().toString(),
      p.getName().toString()
    );
    this.listAll();
  }

  public void updateById() {
    service.update(idProperty().getValue(), getEntity());
    serviceOwner.updatePatientList(
      getIdByName(ownerIdProperty().getValue()),
      nameProperty().getValue()
    );
    this.listAll();
  }

  public void deleteById() {
    service.delete(idProperty().getValue());
    serviceOwner.deleteOnPatientList(
      getIdByName(ownerIdProperty().getValue()),
      idProperty().getValue()
    );
    this.listAll();
  }

  public void listAll() {
    listPatients.clear();
    listPatients.addAll(service.getAllPatients());
  }

  public void findByField() {
    listPatients.clear();
    listPatients.addAll(service.findByField("name", nameProperty().getValue()));
  }

  public ObservableList<String> getAllIdAndNames() {
    List<Pair<String, String>> owners = serviceOwner.getAllIdAndNames();
    ObservableList<String> ownersName = FXCollections.observableArrayList();
    for (Pair<String, String> name : owners) {
      ownersName.addAll(name.getValue());
    }
    return ownersName;
  }

  public String getIdByName(String value) {
    List<Pair<String, String>> owners = serviceOwner.getAllIdAndNames();
    for (Pair<String, String> name : owners) {
      if (name.getValue().equals(value)) {
        return name.getKey();
      }
    }
    return null;
  }

  public String getNameById(String value) {
    List<Pair<String, String>> owners = serviceOwner.getAllIdAndNames();
    for (Pair<String, String> name : owners) {
      if (name.getKey().equals(value)) {
        return name.getValue();
      }
    }
    return null;
  }

  public void clearFields() {
    id.set("");
    ownerId.set("");
    name.set("");
    species.set("");
    family.set("");
    bloodtype.set("");
    obs.set("");
    birthdate.set(null);
    lastVisit.set(null);
    treatment.set("");
  }

  public String timeDateToString(Date value) {
    return fmt.timeDateToString(value);
  }

  public String dateToString(Date value) {
    return fmt.dateToString(value);
  }

  public String treatmentBooleanToString(Boolean value) {
    return fmt.treatmentBooleanToString(value);
  }

  private ObjectId tryToGetId(String property) {
    return (property.isBlank() || property == null)
      ? new ObjectId()
      : new ObjectId(property);
  }

  private Date tryToGetLastVisit(String property) {
    if (property == "" || property == null) {
      return new Date();
    }
    String splittedDate[] = property.split(" às ");

    return fmt.stringToTimeDate(splittedDate[0], splittedDate[1]);
  }

  public ObservableList<Patient> getListPatients() {
    return listPatients;
  }

  public StringProperty idProperty() {
    return id;
  }

  public StringProperty ownerIdProperty() {
    return ownerId;
  }

  public StringProperty nameProperty() {
    return name;
  }

  public StringProperty speciesProperty() {
    return species;
  }

  public StringProperty familyProperty() {
    return family;
  }

  public StringProperty bloodtypeProperty() {
    return bloodtype;
  }

  public StringProperty obsProperty() {
    return obs;
  }

  public ObjectProperty birthdateProperty() {
    return birthdate;
  }

  public StringProperty lastVisitProperty() {
    return lastVisit;
  }

  public StringProperty treatmentProperty() {
    return treatment;
  }
}
