package control;

import dao.PatientDAO;
import dao.PatientDAOImpl;
import entity.Patient;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public class PatientControl {

	private PatientDAO service = new PatientDAOImpl();

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

	void create(Patient patient, ObjectId ownerId) {
		service.insert(patient, ownerId);
	}

	void readAll() {
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

	void readByID(String field, ObjectId id) {
		// ONLY USE '_id' OR 'ownerId'
		Document query = service.findByID(field, id);
		System.out.println(query);
	}

	void readByDate(String field, Date dateGte, Date dateLte) {
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

	void readByField(String field, String data) {
		Document query = service.findByField(field, data);
		System.out.println(query);
	}

	void update(ObjectId id, Patient patient) {
		service.update(id, patient);
	}

	void delete(ObjectId id) {
		service.delete(id);
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
	
}
