package control;

import dao.impl.OwnerDAOImpl;
import dao.impl.PatientDAOImpl;
import dao.interfaces.OwnerDAO;
import dao.interfaces.PatientDAO;
import entity.Owner;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.types.ObjectId;
import utils.Formatters;

public class OwnerControl {

  private ObservableList<Owner> listOwners = FXCollections.observableArrayList();
  private OwnerDAO service = new OwnerDAOImpl();
  private PatientDAO servicePatient = new PatientDAOImpl();
  private Formatters fmt = new Formatters();

  private StringProperty id = new SimpleStringProperty("");
  private StringProperty patientsId = new SimpleStringProperty("");
  private StringProperty email = new SimpleStringProperty("");
  private StringProperty fullname = new SimpleStringProperty("");
  private StringProperty telephoneNumber = new SimpleStringProperty("");
  private StringProperty address = new SimpleStringProperty("");
  private StringProperty identificationNumber = new SimpleStringProperty("");
  private StringProperty lastVisit = new SimpleStringProperty("");

  public Owner getEntity() { //pega dos campos
    Owner owner = new Owner(
      fullnameProperty().getValue(),
      emailProperty().getValue(),
      identificationNumberProperty().getValue()
    );
    owner.setId(tryToGetId(idProperty().getValue()));
    owner.setPatientsId(patientsIdProperty().getValue());
    owner.setTelephoneNumber(telephoneNumberProperty().getValue());
    owner.setAddress(addressProperty().getValue());
    owner.setLastVisit(fmt.stringToDate(lastVisitProperty().getValue()));
    return owner;
  }

  public void setEntity(Owner owner) { //
    id.set(owner.getId().toString());
    patientsId.set(owner.getPatientsId());
    email.set(owner.getEmail());
    fullname.set(owner.getFullname());
    telephoneNumber.set(owner.getTelephoneNumber());
    address.set(owner.getAddress());
    identificationNumber.set(owner.getIdentificationNumber());
    lastVisit.set(fmt.timeDateToString(owner.getLastVisit()));
  }

  public void create() {
    service.insert(getEntity());
    this.listAll();
  }

  public void updateById() {
    service.update(idProperty().getValue(), getEntity());
    this.listAll();
  }

  public void deleteById() {
    service.delete(idProperty().getValue());
    servicePatient.deleteManyByOwnerId(idProperty().getValue());
    this.listAll();
  }

  public void listAll() {
    listOwners.clear();
    listOwners.addAll(service.getAllOwners());
  }

  public void findByField() {
    listOwners.clear();
    listOwners.addAll(
      service.findByField("fullname", fullnameProperty().getValue())
    );
  }

  public void clearFields() {
    id.set("");
    patientsId.set("");
    email.set("");
    fullname.set("");
    telephoneNumber.set("");
    address.set("");
    identificationNumber.set("");
    lastVisit.set("");
    this.listAll();
  }

  public String dateToString(Date value) {
    return fmt.dateToString(value);
  }

  public ObservableList<Owner> getListOwners() {
    return listOwners;
  }

  public StringProperty idProperty() {
    return id;
  }

  public StringProperty patientsIdProperty() {
    return patientsId;
  }

  public StringProperty emailProperty() {
    return email;
  }

  public StringProperty fullnameProperty() {
    return fullname;
  }

  public StringProperty telephoneNumberProperty() {
    return telephoneNumber;
  }

  public StringProperty addressProperty() {
    return address;
  }

  public StringProperty identificationNumberProperty() {
    return identificationNumber;
  }

  public StringProperty lastVisitProperty() {
    return lastVisit;
  }

  public String timeDateToString(Date lt) {
    return fmt.timeDateToString(lt);
  }

  private ObjectId tryToGetId(String id) {
    return (id == "" || id == null)
      ? new ObjectId()
      : new ObjectId(id);
  }
}
