package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entity.Patient;
import org.bson.Document;
import org.bson.conversions.Bson;

public class PatientDao {

  MongoDatabase database;
  MongoCollection<Document> animals;

  void connection() {
    MongoConnect mc = new MongoConnect();
    database = mc.database;
    animals = database.getCollection("animals");
  }

  Document newDocument(Patient animal){
    Document patient = new Document("name", animal.getName())
      .append("clientId", animal.getClientId())
      .append("species", animal.getSpecies())
      .append("family", animal.getFamily())
      .append("bloodtype", animal.getBloodtype())
      .append("obs", animal.getObs())
      .append("birthdate", animal.getBirthdate())
      .append("lastVisit", animal.getLastVisit())
      .append("treatment", animal.getTreatment())
      .append("created", animal.getTreatment());
      return patient;
  }

  void insert(Patient animal) {
    Document patient = newDocument(animal);
    animals.insertOne(patient);
  }

  FindIterable<Document> search(String field, String data) {
    FindIterable<Document> query = animals.find(new Document(field, data));
    return query;
  }

  FindIterable<Document> returnAll() {
    FindIterable<Document> query = animals.find();
    return query;
  }

  FindIterable<Document> searchByDate(
    String field,
    String searchDateGte,
    String searchDateLte
  ) {
    Bson filter = Filters.and(
      Filters.gte(field, searchDateGte),
      Filters.lte(field, searchDateLte)
    );
    FindIterable<Document> query = animals.find(filter);
    return query;
  }

  void update(String id, Patient animal) {
    Document patient = newDocument(animal);
    animals.updateMany(
      Filters.eq("_id", id),
      patient);
  }

  void delete(String id) {
    animals.deleteOne(Filters.eq("_id", id));
  }
}