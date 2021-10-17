package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import entity.Patient;

public class PatientDAOImpl implements PatientDAO {

	  MongoDatabase database;
	  MongoCollection<Document> patients;

	  void connection() {
	    MongoConnect mc = new MongoConnect();
	    database = mc.database;
	    patients = database.getCollection("patients");
	  }

	  Document newDoc(Patient patient) {
	    Document pat = new Document("ownerId", patient.getOwnerId())
	      .append("name", patient.getName())
	      .append("species", patient.getSpecies())
	      .append("family", patient.getFamily())
	      .append("bloodtype", patient.getBloodtype())
	      .append("obs", patient.getObs())
	      .append("birthdate", patient.getBirthdate())
	      .append("lastVisit", patient.getLastVisit())
	      .append("treatment", patient.getTreatment());
	    return pat;
	  }

	  public void insert(Patient patient, ObjectId ownerId) {
	    connection();

	    Document pat = newDoc(patient);

	    pat.put("_id", new ObjectId());
	    pat.put("created", new Date());
	    pat.replace("ownerId", ownerId);

	    patients.insertOne(pat);
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

	  public List<Document> returnAll() {
	    connection();
	    List<Document> query = new ArrayList<Document>();

	    MongoCursor<Document> cursor = patients.find().iterator();

	    try {
	      while (cursor.hasNext()) {
	        System.out.println("--> " + cursor.next());
	        query.add(cursor.next());
	      }
	    } finally {
	      cursor.close();
	    }
	    return query;
	  }

	  public List<Document> findByDate(String field, Date dateGte, Date dateLte) {
	    connection();
	    BasicDBObject betweenDates = new BasicDBObject(
	      field,
	      new Document("$gte", dateGte).append("$lte", dateLte)
	    );

	    List<Document> docPat = new ArrayList<Document>();

	    MongoCursor<Document> cursor = patients.find(betweenDates).iterator();

	    try {
	      while (cursor.hasNext()) {
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
	    pat.put("updated", new Date());

	    BasicDBObject update = new BasicDBObject("$set", pat);

	    patients.updateOne(new BasicDBObject("_id", id), update);
	  }

	  public void delete(ObjectId id) {
	    connection();
	    patients.deleteOne(Filters.eq("_id", id));
	  }
}