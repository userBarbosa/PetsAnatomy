package dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import dao.interfaces.OwnerDAO;
import entity.Owner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javafx.util.Pair;
import org.bson.Document;
import org.bson.types.ObjectId;
import utils.MongoConnect;

public class OwnerDAOImpl implements OwnerDAO {

  MongoCollection<Document> owners;

  private void getCollection() {
    owners = MongoConnect.database.getCollection("owners");
  }

  Document newDoc(Owner owner) {
    Document customer = new Document("fullname", owner.getFullname())
      .append("identificationNumber", owner.getIdentificationNumber())
      .append("email", owner.getEmail())
      .append("telephoneNumber", owner.getTelephoneNumber())
      .append("patientsName", owner.getPatientsName())
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
    o.setPatientsName(doc.getString("patientsName"));
    o.setTelephoneNumber(doc.getString("telephoneNumber"));
    o.setAddress(doc.getString("address"));
    o.setCreated(doc.getDate("created"));
    o.setLastVisit(doc.getDate("lastVisit"));

    return o;
  }

  @Override
  public void insert(Owner owner) {
    Document customer = newDoc(owner);

    customer.put("_id", new ObjectId());
    customer.put("created", new Date());
    getCollection();
    owners.insertOne(customer);
  }

  @Override
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

  @Override
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

  @Override
  public List<Owner> findByField(String field, String data) {
    List<Owner> oList = new ArrayList<Owner>();
    getCollection();
    Pattern regex = Pattern.compile(data, Pattern.CASE_INSENSITIVE);
    MongoCursor<Document> cursor = owners
      .find(new Document(field, regex))
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

  @Override
  public List<Owner> findByDate(String field, Date dateGte, Date dateLt) {
    BasicDBObject betweenDates = new BasicDBObject(
      field,
      new Document("$gte", dateGte).append("$lte", dateLt)
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

  @Override
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

  @Override
  public void update(String id, Owner owner) {
    Document customer = newDoc(owner);

    customer.put("updated", new Date());

    BasicDBObject update = new BasicDBObject("$set", customer);
    getCollection();
    owners.updateOne(new BasicDBObject("_id", new ObjectId(id)), update);
  }

  @Override
  public void updatePatientList(String ownerId, String patientName) {
    getCollection();
    Document owner = new Document();

    try {
      owner = owners.find(new Document("_id", new ObjectId(ownerId))).first();
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (owner != null) {
      String pList = owner.getString("patientsName");
      patientName = patientName.concat(";");
      pList =
        (pList == null || pList.isBlank())
          ? patientName
          : pList.concat(" " + patientName);

      BasicDBObject updated = new BasicDBObject(
        "$set",
        new BasicDBObject("patientsName", pList).append("updated", new Date())
      );
      owners.updateOne(
        new BasicDBObject("_id", new ObjectId(ownerId)),
        updated
      );
    }
  }

  @Override
  public void delete(String id) {
    getCollection();
    owners.deleteOne(new BasicDBObject("_id", new ObjectId(id)));
  }

  @Override
  public void deleteOnPatientList(String ownerId, String patientName) {
    getCollection();
    Document owner = new Document();

    try {
      owner = owners.find(new Document("_id", new ObjectId(ownerId))).first();
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (owner != null) {
      String newList = "";
      String oldList[] = owner.getString("patientsName").split(";");

      if (oldList != null) {
        for (String each : oldList) {
          if (each != null && !each.isBlank()) {
            if (!each.contains(patientName)) {
              newList = newList.concat(each.trim() + "; ");
            }
          }
        }

        BasicDBObject updatedList = new BasicDBObject(
          "$set",
          new BasicDBObject("patientsName", newList).append("updated", new Date())
        );
        owners.updateOne(
          new BasicDBObject("_id", new ObjectId(ownerId)),
          updatedList
        );
      }
    }
  }
}
