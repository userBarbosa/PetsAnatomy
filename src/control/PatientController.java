package control;

import dao.PatientDao;
import entity.Client;
import entity.Patient;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public class PatientController {

  private PatientDao pDao;

  public PatientController() {
    pDao = new PatientDao();
  }

  public static void main(String[] args) {
    PatientController ac = new PatientController();
    // ac.read();
    // Patient cato = new Patient("Lily", null, "Cat", "Felidae", "A", ac.convertToDate(2021, 1, 16), new Date());
    // ac.create(cato);
    ac.read();
  }

  void create(Patient patient) {
    pDao.insert(patient);
  }

  void read() {
    // Document query = aDao.findByID(new ObjectId("6165d09336d596508be4ada8"));
    List<Document> query = (List<Document>) pDao.findByDate(
      "created",
      "2021.10.01",
      "2021.10.12"
    );
    if (query != null) {
      for (Document doc : query) {
        System.out.println(doc.get("_id"));
      }
    } else {
      System.out.println("não encontrado");
    }
  }

  void update(ObjectId id, Patient patient) {
    pDao.update(id, patient);
  }

  void delete(ObjectId id) {
    pDao.delete(id);
  }

  private Date convertToDate(int year, int month, int day) {
    Date date = Date.from(
      LocalDate
        .of(year, month, day)
        .atStartOfDay()
        .atZone(ZoneId.systemDefault())
        .toInstant()
    );

    return date;
  }
}
