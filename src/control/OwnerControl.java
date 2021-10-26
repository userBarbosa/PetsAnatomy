package control;

import dao.OwnerDAO;
import dao.OwnerDAOImpl;
import entity.Owner;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public class OwnerControl {

  private OwnerDAO service = new OwnerDAOImpl();

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

  public void create(Owner owner) {
    service.insert(owner);
  }

  public void readAll() {
    List<Document> allPatients = service.returnAll();
    if (allPatients != null) {
      for (Document doc : allPatients) {
        System.out.println(doc.get("_id"));
      }
    } else {
      System.err.println("There are no clients in database");
    }
  }

  public void readByID(ObjectId id) {
    Document query = service.findByID(id);
    System.out.println(query);
  }

  public void readByDate(String field, Date dateGte, Date dateLte) {
    List<Document> query = service.findByDate(
      field,
      dateGte,
      dateLte
    );
    if (query != null) {
      for (Document doc : query) {
        System.out.println(doc.get("_id"));
      }
    } else {
      System.err.println("There are no owners in specified dates");
    }
  }

  public void readByField(String field, String data) {
    Document query = service.findByField(field, data);
    System.out.println(query);
  }

  public void update(ObjectId id, Owner owner) {
    service.update(id, owner);
  }

  public void delete(ObjectId id) {
    service.delete(id);
  }
}
