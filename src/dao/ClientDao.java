package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entity.Client;
import org.bson.Document;

public class ClientDao {

  MongoDatabase database;
  MongoCollection<Document> clients;

  void connection() {
    MongoConnect mc = new MongoConnect();
    database = mc.database;
    clients = database.getCollection("clients");
  }

  Document newDoc(Client client) {
    Document customer = new Document("fullname", client.getFullname())
      .append("identificationNumber", "")
      .append("email", client.getEmail())
      .append("telephoneNumber", client.getTelephoneNumber())
      .append("animalId", client.getAnimalId())
      .append("identificationNumber", client.getIdentificationNumber())
      .append("address", client.getAddress())
      .append("created", client.getCreated())
      .append("lastVisit", client.getLastVisit());
    return customer;
  }

  void insert(Client client) {
    Document customer = newDoc(client);
    clients.insertOne(customer);
  }

  FindIterable<Document> search(String field, String data) {
    return clients.find(new Document(field, data));
  }

  FindIterable<Document> returnAll() {
    return clients.find();
  }

  void update(String id, Client client) {
    Document customer = newDoc(client);
    clients.updateMany(Filters.eq("_id", id), customer);
  }

  void delete(String id) {
    clients.deleteOne(Filters.eq("_id", id));
  }
}