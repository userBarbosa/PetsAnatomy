package control;

import dao.PatientDao;
import entity.Patient;
import entity.Client;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.bson.Document;

public class PatientControl {

  private Patient patient;
  private Client client;
  private PatientDao aDao;

  public PatientControl() {
    // Patient patient, Client client
    // this.patient = patient;
    // this.client = client;
    // this.aDao = aDao;
    aDao = new PatientDao();
  }

  public static void main(String[] args) {
    PatientControl ac = new PatientControl();
    // ac.read();
    // Patient cato = new Patient("Lily", null, "Cat", "Felidae", "A", ac.convertToDate(2021, 1, 16), new Date());
    // ac.create(cato);
    ac.read();
  }

  void create(Patient patient) {
    aDao.insert(patient);
  }

  void read() {
    // Document query = aDao.findByID(new ObjectId("6165d09336d596508be4ada8"));
    List<Document> query = (List<Document>) aDao.findByDate(
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

  void update() {}

  void delete() {}

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
