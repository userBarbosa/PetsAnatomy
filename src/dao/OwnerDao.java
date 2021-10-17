package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entity.Owner;
import org.bson.Document;
import org.bson.types.ObjectId;

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
      .append("patientsId", owner.getPatientsId())
      .append("identificationNumber", owner.getIdentificationNumber())
      .append("address", owner.getAddress())
      .append("created", owner.getCreated())
      .append("lastVisit", owner.getLastVisit());
    return customer;
  }

  public void insert(Owner owner) {
    connection();

    Document customer = newDoc(owner);

    customer.put("_id", new ObjectId());
    customer.put("created", new Date());

    owners.insertOne(customer);
  }

  public Document findByField(String field, String data) {
    try {
      connection();

      Document query = owners.find(new Document(field, data)).first();
      return query;
    } catch (Exception e) {
      System.err.println(e);
    }
    return null;
  }

  public Document findByID(String field, ObjectId objectId) {
    try {
      connection();

      Document query = owners.find(new Document(field, objectId)).first();
      return query;
    } catch (Exception e) {
      System.err.println(e);
    }
    return null;
  }

  public List<Document> returnAll() {
    connection();
    List<Document> query = new ArrayList<Document>();

    MongoCursor<Document> cursor = owners.find().iterator();

    try {
      while (cursor.hasNext()) {
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

    List<Document> docOwner = new ArrayList<Document>();

    MongoCursor<Document> cursor = owners.find(betweenDates).iterator();

    try {
      while (cursor.hasNext()) {
        docOwner.add(cursor.next());
      }
    } finally {
      cursor.close();
    }

    return docOwner;
  }

  void update(String id, Owner owner) {
    connection();
    Document customer = newDoc(owner);
    
    customer.put("updated", new Date());

    BasicDBObject update = new BasicDBObject("$set", customer);

    owners.updateOne(new BasicDBObject("_id", id), update);

  }

  void delete(String id) {
    connection();
    owners.deleteOne(Filters.eq("_id", id));
  }
}