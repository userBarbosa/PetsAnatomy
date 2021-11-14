package control;

import java.time.LocalDate;

import org.bson.types.ObjectId;

import dao.impl.PatientDAOImpl;
import dao.interfaces.PatientDAO;
import entity.Patient;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Formatters;

public class PatientControl {

  private ObservableList<Patient> listPatients = FXCollections.observableArrayList();
  private PatientDAO service = new PatientDAOImpl();
  private Formatters fmt = new Formatters();
  
  private StringProperty id = new SimpleStringProperty("");
  private StringProperty ownerId = new SimpleStringProperty("");
  private StringProperty name = new SimpleStringProperty("");
  private StringProperty species = new SimpleStringProperty("");
  private StringProperty family = new SimpleStringProperty("");
  private StringProperty bloodtype = new SimpleStringProperty("");
  private StringProperty obs = new SimpleStringProperty("");
  private ObjectProperty birthdate = new SimpleObjectProperty();
  private ObjectProperty lastVisit = new SimpleObjectProperty();
  private StringProperty treatment = new SimpleStringProperty("");

  public Patient getEntity() {
	  Patient patient = new Patient(
			  nameProperty().getValue(),
			  new ObjectId(ownerIdProperty().getValue()),
			  speciesProperty().getValue(),
			  familyProperty().getValue()
			  );
	  patient.setId((idProperty().getValue() == "" || idProperty().getValue() == null) ? new ObjectId() : new ObjectId(idProperty().getValue()));
	  patient.setBloodtype(bloodtypeProperty().getValue());
	  patient.setObs(obsProperty().getValue());
	  patient.setBirthdate(fmt.localToDate((LocalDate) birthdateProperty().getValue()));
	  patient.setLastVisit(fmt.localToDate((LocalDate) lastVisitProperty().getValue()));
	  patient.setTreatment(fmt.treatmentStringToBoolean(treatmentProperty().getValue()));
	  return patient;
  }
  
  public void setEntity(Patient patient) {
      id.set((String) patient.getId().toString());
      ownerId.set((String) patient.getOwnerId().toString());
      name.set(patient.getName());
      species.set(patient.getSpecies());
      family.set(patient.getFamily());
      bloodtype.set(patient.getBloodtype());
      obs.set(patient.getObs());
      birthdate.set(fmt.DateToLocal(patient.getBirthdate()));
      lastVisit.set(fmt.DateToLocal(patient.getLastVisit()));
      treatment.set(fmt.treatmentBooleanToString(patient.getTreatment()));
  }

  public void create() {
    service.insert(getEntity(), ownerIdProperty().getValue());
    this.listAll();
  }

  public void updateById() {
    service.update(idProperty().getValue(), getEntity());
    this.listAll();
  }

  public void deleteById() {
    service.delete(idProperty().getValue());
    this.findByField();
  }

  public void listAll() {
	listPatients.clear();
	listPatients.addAll(service.getAllPatients());
  }

  public void findByField() {
	  listPatients.clear();
	  listPatients.addAll(service.findByField("name", nameProperty().getValue()));
  }

  public void clearFields() {
    id.set(null);
    ownerId.set(null);
    name.set("");
    species.set("");
    family.set("");
    bloodtype.set("");
    obs.set("");
    birthdate.set(null);
    lastVisit.set(null);
    treatment.set("");
    this.listAll();
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

  public ObjectProperty lastVisitProperty() {
    return lastVisit;
  }

  public StringProperty treatmentProperty() {
    return treatment;
  }
  
}
