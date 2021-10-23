package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entity.Employee;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import security.Config;

public class EmployeeDAOImpl implements EmployeeDAO {

  MongoDatabase database;
  MongoCollection<Document> employees;

  void connection() {
    MongoConnect mc = new MongoConnect();
    database = mc.database;
    employees = database.getCollection("employees");
  }

  Document newDoc(Employee employee) {
    Document worker = new Document("username", employee.getUsername())
      .append("active", employee.getActive())
      .append("email", employee.getEmail())
      .append("fullname", employee.getFullname())
      .append("password", employee.getPassword())
      .append("role", employee.getRole())
      .append("telephoneNumber", employee.getTelephoneNumber())
      .append("bankDetails", employee.getBankDetails())
      .append("birthDate", employee.getBirthDate())
      .append("specialty", employee.getSpecialty());
    return worker;
  }

  public void insert(Employee employee) {
    connection();

    Document worker = newDoc(employee);

    worker.put("_id", new ObjectId());
    worker.put("created", new Date());

    employees.insertOne(worker);
  }

  public Document findByID(ObjectId id) {
    try {
      connection();

      Document query = employees.find(new Document("_id", id)).first();
      return query;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public Document findByField(String field, String data) {
    try {
      connection();
      String regexQuery = "/^" + data + "/";
      Document query = employees.find(new Document(field, regexQuery)).first();
      return query;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public boolean findLoginData(String username, String password) {
    try {
      connection();

      Document query = employees
        .find(new Document("username", username))
        .first();

      if (query.get("password") == new Config().encryptPassword(password)) {
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public boolean findToCreateUser(String username, String email) {
    try {
      connection();
      if (employees.find(new Document("username", username)) != null && employees.find(new Document("email", email)) != null) {
				return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public List<Document> findByDate(String field, Date dateGte, Date dateLte) {
    connection();

    BasicDBObject betweenDates = new BasicDBObject(
      field,
      new Document("$gte", dateGte).append("$lte", dateLte)
    );

    List<Document> docWorker = new ArrayList<Document>();

    MongoCursor<Document> cursor = employees.find(betweenDates).iterator();

    try {
      while (cursor.hasNext()) {
        docWorker.add(cursor.next());
      }
    } finally {
      cursor.close();
    }

    return docWorker;
  }

  public List<Document> returnAll() {
    List<Document> docWorker = new ArrayList<Document>();

    MongoCursor<Document> cursor = employees.find().iterator();

    try {
      while (cursor.hasNext()) {
        docWorker.add(cursor.next());
      }
    } finally {
      cursor.close();
    }

    return docWorker;
  }

  public void update(ObjectId id, Employee employee) {
    connection();

    Document worker = newDoc(employee);
    worker.put("updated", new Date());

    employees.updateOne(
      new BasicDBObject("_id", id),
      new BasicDBObject("$set", worker)
    );
  }

  public void delete(ObjectId id) {
    connection();
    employees.deleteOne(Filters.eq("_id", id));
  }
}
