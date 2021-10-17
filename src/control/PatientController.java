package control;

import dao.PatientDao;
import entity.Patient;

import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public class PatientController {

  private PatientDao pDao;

  public PatientController() {
    pDao = new PatientDao();
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

  void create(Patient patient, ObjectId ownerId) {
    pDao.insert(patient, ownerId);
  }

  void readAll() {
    pDao.returnAll();
  }

  void readByID(String field, ObjectId id) {
    // ONLY USE '_id' OR 'ownerId'
    Document query = pDao.findByID(field, id);
    // TODO: return data method to boundary
    System.out.println(query);
  }

  void readByDate(String field, Date dateGte, Date dateLte) {
    List<Document> query = (List<Document>) pDao.findByDate(
      field,
      dateGte,
      dateLte
    );
    // TODO: return data method to boundary
    if (query != null) {
      for (Document doc : query) {
        System.out.println(doc.get("_id"));
      }
    } else {
      // Throw error?
      System.err.println("There are no patients in this variables");
    }
  }

  void update(ObjectId id, Patient patient) {
    pDao.update(id, patient);
  }

  void delete(ObjectId id) {
    pDao.delete(id);
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