package control;

import dao.PatientDAO;
import dao.PatientDAOImpl;
import entity.Patient;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public class PatientControl {

	private ObservableList<Patient> patients = FXCollections.observableArrayList();
	private TableView<Patient> table = new TableView<Patient>();
	private PatientDAO service = new PatientDAOImpl();

	private ObjectProperty id = new SimpleObjectProperty("");
	private ObjectProperty ownerId = new SimpleObjectProperty("");
	private StringProperty name = new SimpleStringProperty("");
	private StringProperty species = new SimpleStringProperty("");
	private StringProperty family = new SimpleStringProperty("");
	private StringProperty bloodtype = new SimpleStringProperty("");
	private StringProperty obs = new SimpleStringProperty("");
	private ObjectProperty birthdate = new SimpleObjectProperty();
	private ObjectProperty lastVisit = new SimpleObjectProperty();
	private ObjectProperty created = new SimpleObjectProperty();
	private ObjectProperty updated = new SimpleObjectProperty();
	private BooleanProperty treatment = new SimpleBooleanProperty();

	public void setEntity(Patient patient) {
		if (patient != null) {
			id.set(patient.getId());
			ownerId.set(patient.getOwnerId());
			name.set(patient.getName());
			species.set(patient.getSpecies());
			family.set(patient.getFamily());
			bloodtype.set(patient.getBloodtype());
			obs.set(patient.getObs());
			birthdate.set(patient.getBirthdate());
			lastVisit.set(patient.getLastVisit());
			created.set(patient.getCreated());
			updated.set(patient.getUpdated());
			treatment.set(patient.getTreatment());
		}
	}

	public Patient getEntity() {
		Patient patient = new Patient();
		patient.setId((ObjectId) id.get());
		patient.setOwnerId((ObjectId) ownerId.get());
		patient.setName(name.get());
		patient.setSpecies(species.get());
		patient.setFamily(family.get());
		patient.setBloodtype(bloodtype.get());
		patient.setObs(obs.get());
		patient.setBirthdate((Date) birthdate.get());
		patient.setLastVisit((Date) lastVisit.get());
		patient.setCreated((Date) created.get());
		patient.setUpdated((Date) updated.get());
		patient.setTreatment(treatment.get());
		return patient;
	}

	public void create() {
		service.insert(getEntity(), getOwnerId());
		this.listAll();
	}

	public void updateById() {
		service.update(getId(), getEntity());
		this.listAll();
	}

	public void deleteById() {
		service.delete(getId());
		this.findByName();	
	}

	private void listAll() {
		patients.clear();
		patients.addAll( service.findByName("") );
	}

	public void findByName() {
		patients.clear();
		patients.addAll( service.findByName( getName() ));
	}

	public void clearFields() {
		Patient patient = new Patient();
		patient.setId(null);
		id.set(0);
		ownerId.set(0);
		name.set("");
		species.set("");
		family.set("");
		bloodtype.set("");
		obs.set("");
		birthdate.set(null);
		lastVisit.set(null);
		created.set(null);
		updated.set(null);
		treatment.set(false);
		this.listAll();
	}

	public void generatedTable() {
		listAll();
		TableColumn<Patient, ObjectId> colId = new TableColumn<>("Id");
		colId.setCellValueFactory(new PropertyValueFactory<Patient, ObjectId>("id"));

		TableColumn<Patient, ObjectId> colOwner = new TableColumn<>("Dono");
		colOwner.setCellValueFactory(new PropertyValueFactory<Patient, ObjectId>("ownerId"));

		TableColumn<Patient, String> colName = new TableColumn<>("Nome");
		colName.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));

		TableColumn<Patient, String> colSpecies = new TableColumn<>("Espécie");
		colSpecies.setCellValueFactory(new PropertyValueFactory<Patient, String>("species"));

		TableColumn<Patient, String> colFamily = new TableColumn<>("Família");
		colFamily.setCellValueFactory(new PropertyValueFactory<Patient, String>("family"));

		TableColumn<Patient, String> colBloodtype = new TableColumn<>("Tipo Sanguíneo");
		colBloodtype.setCellValueFactory(new PropertyValueFactory<Patient, String>("bloodtype"));

		TableColumn<Patient, String> colBirthdate = new TableColumn<>("Data de Nascimento");
		colBirthdate.setCellValueFactory( (patientProp) -> {
			Date n = patientProp.getValue().getBirthdate();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String strData = dateFormat.format(n);
			return new ReadOnlyStringWrapper(strData);
		} );

		TableColumn<Patient, String> colObs = new TableColumn<>("Observação");
		colObs.setCellValueFactory(new PropertyValueFactory<Patient, String>("obs"));

		TableColumn<Patient, String> colLastVisit = new TableColumn<>("Última Consulta");
		colLastVisit.setCellValueFactory( (patientProp) -> {
			Date n = patientProp.getValue().getLastVisit();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String strData = dateFormat.format(n);
			return new ReadOnlyStringWrapper(strData);
		} );

		TableColumn<Patient, String> colTreatment = new TableColumn<>("Em Tratamento");
		colTreatment.setCellValueFactory(new PropertyValueFactory<Patient, String>("treatment"));

		table.getColumns().addAll(colId, colOwner, colName, colSpecies, colFamily, colBloodtype, colBirthdate, colObs, colLastVisit, colTreatment);

		table.getSelectionModel().selectedItemProperty().addListener(
				(obs, antigo, novo) -> {
					setEntity(novo);
				}
				);

		table.setItems(patients);
	}

	/* // tests structures; remove for final application:
    public static void main(String[] args) {
      PatientController ac = new PatientController();
      ac.readByDate("created", ac.convertToDate(2021, 10, 01), ac.convertToDate(2021, 10, 30));
       Patient cato = new Patient(
        "Lily",
        new ObjectId("616c8dc37201795740d181e9"),
        "Cat",
        "Felidae",
        "A",
        ac.convertToDate(2021, 1, 16)
      );
      // ac.create(cato);
      // ac.delete(new ObjectId("6165d09336d596508be4ada8"));
      // ac.update(new ObjectId("616ba03f0e4bfe7860e55b5f"), cato);
    }  */

	public void readAll() {
		List<Document> allPatients = service.returnAll();
		if (allPatients != null) {
			for (Document doc : allPatients) {
				System.out.println(doc.get("_id"));
			}
		} else {
			// Throw error?
			System.err.println("There are no patients in database");
		}
	}

	public void readByID(String field, ObjectId id) {
		// ONLY USE '_id' OR 'ownerId'
		Document query = service.findByID(field, id);
		System.out.println(query);
	}

	public void readByDate(String field, Date dateGte, Date dateLte) {
		List<Document> query = (List<Document>) service.findByDate(
				field,
				dateGte,
				dateLte
				);
		if (query != null) {
			for (Document doc : query) {
				System.out.println(doc.get("_id"));
			}
		} else {
			// Throw error?
			System.err.println("There are no patients in this variables");
		}
	}

	public void readByField(String field, String data) {
		Document query = service.findByField(field, data);
		System.out.println(query);
	}

	/* private Date convertToDate(int year, int month, int day) {
      Date date = Date.from(
        LocalDate
          .of(year, month, day)
          .atStartOfDay()
          .atZone(ZoneId.systemDefault())
          .toInstant()
      );
      return date;
    } */

	public TableView<Patient> getTable() {
		return table;
	}

	public ObjectId getId() {
		return (ObjectId) id.get();
	}

	public ObjectProperty idProperty() {
		return id;
	}

	public ObjectId getOwnerId() {
		return (ObjectId) ownerId.get();
	}

	public ObjectProperty ownerIdProperty() {
		return ownerId;
	}

	public String getName() {
		return name.get();
	}

	public StringProperty nameProperty() {
		return name;
	}

	public String getSpecies() {
		return species.get();
	}

	public StringProperty speciesProperty() {
		return species;
	}

	public String getFamily() {
		return family.get();
	}

	public StringProperty familyProperty() {
		return family;
	}

	public String getBloodtype() {
		return bloodtype.get();
	}

	public StringProperty bloodtypeProperty() {
		return bloodtype;
	}

	public String getObs() {
		return obs.get();
	}	

	public StringProperty obsProperty() {
		return obs;
	}

	public Object getBirthdate() {
		return birthdate.get();
	}

	public ObjectProperty birthdateProperty() {
		return birthdate;
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

	public Boolean getTreatment() {
		return treatment.get();
	}

	public BooleanProperty treatmentProperty() {
		return treatment;
	}

}
