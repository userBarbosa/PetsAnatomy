package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entity.patient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class patientDao {

  MongoDatabase database;
  MongoCollection<Document> patients;

  void connection() {
    MongoConnect mc = new MongoConnect();
    database = mc.database;
    patients = database.getCollection("patients");
  }

  Document newDoc(patient patient) {
    Document patient1 = new Document("_id", patient.getId())
      .append("name", patient.getName())
      .append("clientId", patient.getClientId())
      .append("species", patient.getSpecies())
      .append("family", patient.getFamily())
      .append("bloodtype", patient.getBloodtype())
      .append("obs", patient.getObs())
      .append("birthdate", patient.getBirthdate())
      .append("lastVisit", patient.getLastVisit())
      .append("treatment", patient.getTreatment())
      .append("created", patient.getCreated());
    return patient1;
  }

  public void insert(patient patient) {
    connection();
    patients.insertOne(newDoc(patient));
  }

  public Document findByField(String field, String data) {
    try {
      connection();

      Document query = patients.find(new Document(field, data)).first();
      return query;
    } catch (Exception e) {
      System.err.println(e);
    }
    return null;
  }

  public Document findByID(ObjectId objectId) {
    try {
      connection();

      Document query = patients.find(new Document("_id", objectId)).first();
      return query;
    } catch (Exception e) {
      System.err.println(e);
    }
    return null;
  }

  public FindIterable<Document> returnAll() {
    connection();
    FindIterable<Document> query = patients.find();
    return query;
  }

  // STILL NOT WORKING, ARE ERRORS ON THE QUERY
  public List<Document> findByDate(
    String field,
    String searchDateGte,
    String searchDateLte
  ) {
    connection();

    BasicDBObject betweenDates = new BasicDBObject(
      field,
      new Document("$gte", searchDateGte).append("$lte", searchDateLte)
    );

    System.out.println("--> " + betweenDates);

    List<Document> docpatient1 = new ArrayList<Document>();

    MongoCursor<Document> cursor = patients.find(betweenDates).iterator();

    try {
      while (cursor.hasNext()) {
        System.out.println("--> " + cursor.next());
        docpatient1.add(cursor.next());
      }
    } finally {
      cursor.close();
    }

    return docpatient1;
  }

  public void update(String id, patient patient) {
    Document patient1 = newDoc(patient);
    patients.updateMany(Filters.eq("_id", id), patient1);
  }

  public void delete(String id) {
    patients.deleteOne(Filters.eq("_id", id));
  }
}
