package dao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import org.bson.Document;
import org.bson.types.ObjectId;

import entity.Patient;

public class PatientDao {

  MongoDatabase database;
  MongoCollection<Document> patients;

  void connection() {
    MongoConnect mc = new MongoConnect();
    database = mc.database;
    patients = database.getCollection("patients");
  }

  Document newDoc(Patient patient) {
    Document pat = new Document("_id", patient.getId())
      .append("name", patient.getName())
      .append("species", patient.getSpecies())
      .append("family", patient.getFamily())
      .append("bloodtype", patient.getBloodtype())
      .append("obs", patient.getObs())
      .append("birthdate", patient.getBirthdate())
      .append("lastVisit", patient.getLastVisit())
      .append("treatment", patient.getTreatment())
      .append("created", patient.getCreated());
    return pat;
  }

  public void insert(Patient patient, ObjectId ownerId) {
    connection();
    patients.insertOne(newDoc(patient)
    .append("ownerId", ownerId));
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

  public Document findByID(String field, ObjectId objectId) {
    try {
      connection();

      Document query = patients.find(new Document(field, objectId)).first();
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
    String dateGte,
    String dateLte
  ) {
    /* db.patients.find({
      created: {
        $gte: {ISODate(dateGte)},
        $lte: {ISODate(dateLte)}
      }
    }) */
    connection();

    BasicDBObject betweenDates = new BasicDBObject(
      field,
      new Document("$gte", dateGte).append("$lte", dateLte)
    );

    System.out.println("--> " + betweenDates);

    List<Document> docPat = new ArrayList<Document>();

    MongoCursor<Document> cursor = patients.find(betweenDates).iterator();

    try {
      while (cursor.hasNext()) {
        System.out.println("--> " + cursor.next());
        docPat.add(cursor.next());
      }
    } finally {
      cursor.close();
    }

    return docPat;
  }

  public void update(ObjectId id, Patient patient) {
    connection();
    Document pat = newDoc(patient);
    pat.replace("_id", id);
    patients.updateMany(Filters.eq("_id", id), pat);
  }

  public void delete(ObjectId id) {
    connection();
    patients.deleteOne(Filters.eq("_id", id));
  }
}
