package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entity.Owner;
import org.bson.Document;

public class OwnerDao {

  MongoDatabase database;
  MongoCollection<Document> owners;

  void connection() {
    MongoConnect mc = new MongoConnect();
    database = mc.database;
    owners = database.getCollection("owners");
  }

  Document newDoc(Owner owner) {
    Document customer = new Document("fullname", owner.getFullname())
      .append("identificationNumber", "")
      .append("email", owner.getEmail())
      .append("telephoneNumber", owner.getTelephoneNumber())
      .append("patientId", owner.getpatientId())
      .append("identificationNumber", owner.getIdentificationNumber())
      .append("address", owner.getAddress())
      .append("created", owner.getCreated())
      .append("lastVisit", owner.getLastVisit());
    return customer;
  }

  void insert(Owner owner) {
    Document customer = newDoc(owner);
    owners.insertOne(customer);
  }

  FindIterable<Document> findByField(String field, String data) {
    return owners.find(new Document(field, data));
  }

  FindIterable<Document> returnAll() {
    return owners.find();
  }

  void update(String id, Owner owner) {
    Document customer = newDoc(owner);
    owners.updateMany(Filters.eq("_id", id), customer);
  }

  void delete(String id) {
    owners.deleteOne(Filters.eq("_id", id));
  }
}