package control;

import dao.OwnerDAO;
import dao.OwnerDAOImpl;
import entity.Owner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.types.ObjectId;

public class OwnerControl {

  private ObservableList<Owner> owners = FXCollections.observableArrayList();
  private TableView<Owner> table = new TableView<Owner>();
  private OwnerDAO service = new OwnerDAOImpl();

  private StringProperty id = new SimpleStringProperty("");
  private StringProperty patientsId = new SimpleStringProperty("");
  private StringProperty email = new SimpleStringProperty("");
  private StringProperty fullname = new SimpleStringProperty("");
  private StringProperty telephoneNumber = new SimpleStringProperty("");
  private StringProperty address = new SimpleStringProperty("");
  private StringProperty identificationNumber = new SimpleStringProperty("");
  private ObjectProperty lastVisit = new SimpleObjectProperty();
  private ObjectProperty created = new SimpleObjectProperty();
  private ObjectProperty updated = new SimpleObjectProperty();

  public void setEntity(Owner owner) {
    if (owner != null) {
      id.set(owner.getId().toString());
      patientsId.set(owner.getPatientsId());
      email.set(owner.getEmail());
      fullname.set(owner.getEmail());
      telephoneNumber.set(owner.getTelephoneNumber());
      address.set(owner.getAddress());
      identificationNumber.set(owner.getIdentificationNumber());
      lastVisit.set(owner.getLastVisit());
      created.set(owner.getCreated());
      updated.set(owner.getUpdated());
    }
  }

  public Owner getEntity() {
    Owner owner = new Owner(
      fullnameProperty().getValue(),
      emailProperty().getValue(),
      identificationNumberProperty().getValue()
    );
    owner.setId(new ObjectId(id.get()));
    owner.setPatientsId(patientsId.get());
    owner.setTelephoneNumber(telephoneNumber.get());
    owner.setAddress(address.get());
    owner.setLastVisit((Date) lastVisit.get());
    owner.setCreated((Date) created.get());
    owner.setUpdated((Date) updated.get());
    return owner;
  }

  ObservableList<String> toObservableList(List<String> l) {
    ObservableList<String> obsList = FXCollections.observableArrayList(l);
    return obsList;
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
    this.findByIdentificationNumber();
  }

  private void listAll() {
    owners.clear();
    owners.addAll(service.findByField("", ""));
  }

  public void findByIdentificationNumber() {
    owners.clear();
    // owners.addAll( service.findByIdentificationNumber( getIdentificationNumber() ));
  }

  public void clearFields() {
    id.set("");
    patientsId.set("");
    email.set("");
    fullname.set("");
    telephoneNumber.set("");
    address.set("");
    identificationNumber.set("");
    lastVisit.set(null);
    created.set(null);
    updated.set(null);
    this.listAll();
  }

  public void generatedTable() {
    listAll();
    TableColumn<Owner, ObjectId> colPatients = new TableColumn<>("Pacientes");
    colPatients.setCellValueFactory(
      new PropertyValueFactory<Owner, ObjectId>("patientsId")
    );

    TableColumn<Owner, String> colEmail = new TableColumn<>("Email");
    colEmail.setCellValueFactory(
      new PropertyValueFactory<Owner, String>("email")
    );

    TableColumn<Owner, String> colFullname = new TableColumn<>("Nome Completo");
    colFullname.setCellValueFactory(
      new PropertyValueFactory<Owner, String>("fullname")
    );

    TableColumn<Owner, String> colTelephoneNumber = new TableColumn<>(
      "Telefone"
    );
    colTelephoneNumber.setCellValueFactory(
      new PropertyValueFactory<Owner, String>("telephoneNumber")
    );

    TableColumn<Owner, String> colAddress = new TableColumn<>("Endereço");
    colAddress.setCellValueFactory(
      new PropertyValueFactory<Owner, String>("address")
    );

    TableColumn<Owner, String> colIdentificationNumber = new TableColumn<>(
      "Número Documento"
    );
    colIdentificationNumber.setCellValueFactory(
      new PropertyValueFactory<Owner, String>("identificationNumber")
    );

    TableColumn<Owner, String> colLastVisit = new TableColumn<>(
      "Última Consulta"
    );
    colLastVisit.setCellValueFactory(
      ownerProp -> {
        Date n = ownerProp.getValue().getLastVisit();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strData = dateFormat.format(n);
        return new ReadOnlyStringWrapper(strData);
      }
    );

    TableColumn<Owner, String> colCreated = new TableColumn<>(
      "Data de Criação"
    );
    colCreated.setCellValueFactory(
      ownerProp -> {
        Date n = ownerProp.getValue().getCreated();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strData = dateFormat.format(n);
        return new ReadOnlyStringWrapper(strData);
      }
    );

    TableColumn<Owner, String> colUpdated = new TableColumn<>(
      "Data Atualização"
    );
    colUpdated.setCellValueFactory(
      ownerProp -> {
        Date n = ownerProp.getValue().getUpdated();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strData = dateFormat.format(n);
        return new ReadOnlyStringWrapper(strData);
      }
    );

    table
      .getColumns()
      .addAll(
        colPatients,
        colEmail,
        colFullname,
        colTelephoneNumber,
        colAddress,
        colIdentificationNumber,
        colLastVisit,
        colCreated,
        colUpdated
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

    table.setItems(owners);
  }

  public void readAll() {
    List<Owner> allPatients = service.getAllOwners();
    if (allPatients != null) {
      for (Owner doc : allPatients) {
        System.out.println(doc.getId());
      }
    } else {
      System.err.println("There are no clients in database");
    }
  }

  public void readByID(String id) {
    Owner query = service.findByID(id);
    System.out.println(query);
  }

  public void readByDate(String field, Date dateGte, Date dateLte) {
    List<Owner> oList = service.findByDate(field, dateGte, dateLte);
    if (oList != null) {
      for (Owner doc : oList) {
        System.out.println(doc.getId());
      }
    } else {
      System.err.println("There are no owners in specified dates");
    }
  }

  public void readByField(String field, String data) {
    List<Owner> oList = service.findByField(field, data);
    if (oList != null) {
      for (Owner doc : oList) {
        System.out.println(doc.getId());
      }
    } else {
      System.err.println("There are no owners in the specified field");
    }
  }

  /* tests structures; remove for final application:
	  public static void main(String[] args) {
	    OwnerControl oc = new OwnerControl();
	    Owner aninha = new Owner(
	      "Karinha",
	      "email@email.com",
	      "11999999999",
	      "653342342",
	      "RG",
	      "R. Rueira"
	    );
	    aninha.setPatientsId(new ObjectId());
	    oc.create(aninha);
	    oc.readAll();
	    oc.readByID(new ObjectId("616e240fe4162974d945604a"));
	    oc.readByDate(
	      "created",
	      new Date().from(Instant.now().minusMillis((86400000))),
	      new Date().from(Instant.now().plusMillis(86400000))
	    );
	    oc.readByField("fullname", "Ana Beatriz");
	    oc.update(new ObjectId("616e240fe4162974d945604a"), aninha);
	    oc.delete(new ObjectId("616e25c158bdb637445e75fc"));
	  }*/

  public TableView<Owner> getTable() {
    return table;
  }

  public String getId() {
    return id.get();
  }

  public StringProperty idProperty() {
    return id;
  }

  public String getPatientsId() {
    return patientsId.get();
  }

  public StringProperty patientsIdProperty() {
    return patientsId;
  }

  public String getEmail() {
    return email.get();
  }

  public StringProperty emailProperty() {
    return email;
  }

  public String getFullname() {
    return fullname.get();
  }

  public StringProperty fullnameProperty() {
    return fullname;
  }

  public String getTelephoneNumber() {
    return telephoneNumber.get();
  }

  public StringProperty telephoneNumberProperty() {
    return telephoneNumber;
  }

  public String getAddress() {
    return address.get();
  }

  public StringProperty addressProperty() {
    return address;
  }

  public String getIdentificationNumber() {
    return identificationNumber.get();
  }

  public StringProperty identificationNumberProperty() {
    return identificationNumber;
  }

  public Object getLastVisit() {
    return lastVisit.get();
  }

  public ObjectProperty lastVisitProperty() {
    return lastVisit;
  }

  public Object getCreated() {
    return created.get();
  }

  public ObjectProperty createdProperty() {
    return created;
  }

  public Object getUpdated() {
    return updated.get();
  }

  public ObjectProperty updatedProperty() {
    return updated;
  }
}
