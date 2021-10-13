package dao;

import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entity.Animal;
import org.bson.Document;
import org.bson.conversions.Bson;

public class AnimalDao {

  MongoDatabase database;
  MongoCollection<Document> animals;

  void connection() {
    MongoConnect mc = new MongoConnect();
    database = mc.database;
    animals = database.getCollection("animals");
  }

  Document newDoc(Animal animal){
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

  public void insert(Animal animal) {
    Document patient = newDoc(animal);
    animals.insertOne(patient);
  }

  public Document search(String field, String data) {
    try {
      connection();

      FindIterable<Document> query = animals.find(new Document(field, data));

    } catch (Exception e) {
      System.err.println(e);
    }
    return null;
  }

  public FindIterable<Document> returnAll() {
    FindIterable<Document> query = animals.find();
    return query;
  }

  public FindIterable<Document> searchByDate(
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

  public void update(String id, Animal animal) {
    Document patient = newDoc(animal);
    animals.updateMany(
      Filters.eq("_id", id),
      patient);
  }

  public void delete(String id) {
    animals.deleteOne(Filters.eq("_id", id));
  }
}