package dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import dao.interfaces.OwnerDAO;
import entity.Owner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.util.Pair;
import org.bson.Document;
import org.bson.types.ObjectId;
import utils.MongoConnect;

public class OwnerDAOImpl implements OwnerDAO {

  MongoCollection<Document> owners;
  MongoConnect mc = new MongoConnect();

  public void getCollection() {
    owners = mc.database.getCollection("owners");
  }

  Document newDoc(Owner owner) {
    Document customer = new Document("fullname", owner.getFullname())
      .append("identificationNumber", "")
      .append("email", owner.getEmail())
      .append("telephoneNumber", owner.getTelephoneNumber())
      .append("patientsId", owner.getPatientsId())
      .append("identificationNumber", owner.getIdentificationNumber())
      .append("address", owner.getAddress())
      .append("lastVisit", owner.getLastVisit());
    return customer;
  }

  Owner newOwner(Document doc) {
    String fullname = doc.getString("fullname");
    String email = doc.getString("email");
    String identificationNumber = doc.getString("identificationNumber");

    Owner o = new Owner(fullname, email, identificationNumber);

    o.setId(doc.getObjectId("_id"));
    o.setTelephoneNumber(doc.getString("telephoneNumber"));
    o.setAddress(doc.getString("address"));
    o.setCreated(doc.getDate("created"));
    o.setLastVisit(doc.getDate("lastVisit"));

    return o;
  }

  public void insert(Owner owner) {
    Document customer = newDoc(owner);

    customer.put("_id", new ObjectId());
    customer.put("created", new Date());
    getCollection();
    owners.insertOne(customer);
  }

  public Owner findByID(String id) {
    Document query = new Document();
    getCollection();
    try {
      query = owners.find(new Document("_id", new ObjectId(id))).first();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return newOwner(query);
  }

  public List<Owner> findByField(String field, String data) {
    List<Owner> oList = new ArrayList<Owner>();
    getCollection();
    MongoCursor<Document> cursor = owners
      .find(new Document(field, data))
      .iterator();

    try {
      while (cursor.hasNext()) {
        oList.add(newOwner(cursor.next()));
      }
    } finally {
      cursor.close();
    }

    return oList;
  }

  public List<Owner> getAllOwners() {
    List<Owner> oList = new ArrayList<Owner>();
    getCollection();
    MongoCursor<Document> cursor = owners.find().iterator();

    try {
      while (cursor.hasNext()) {
        oList.add(newOwner(cursor.next()));
      }
    } finally {
      cursor.close();
    }
    return oList;
  }

  public List<Owner> findByDate(String field, Date dateGte, Date dateLte) {
    BasicDBObject betweenDates = new BasicDBObject(
      field,
      new Document("$gte", dateGte).append("$lte", dateLte)
    );

    List<Owner> oList = new ArrayList<Owner>();
    getCollection();
    MongoCursor<Document> cursor = owners.find(betweenDates).iterator();

    try {
      while (cursor.hasNext()) {
        oList.add(newOwner(cursor.next()));
      }
    } finally {
      cursor.close();
    }

    return oList;
  }

  public void update(String id, Owner owner) {
    Document customer = newDoc(owner);

    customer.put("updated", new Date());

    BasicDBObject update = new BasicDBObject("$set", customer);
    getCollection();
    owners.updateOne(new BasicDBObject("_id", new ObjectId(id)), update);
  }

  public void delete(String id) {
    getCollection();
    owners.deleteOne(Filters.eq("_id", new ObjectId(id)));
  }

  public List<Pair<String, String>> getAllIdAndNames() {
    List<Pair<String, String>> cbList = new ArrayList<Pair<String, String>>();
    getCollection();
    MongoCursor<Document> cursor = owners.find().iterator();

    try {
      while (cursor.hasNext()) {
        Document temp = cursor.next();
        cbList.add(
          new Pair<String, String>(
            temp.get("_id").toString(),
            temp.get("fullname").toString()
          )
        );
      }
    } finally {
      cursor.close();
    }

    return cbList;
  }
}
