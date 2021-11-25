package dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.bson.Document;
import org.bson.types.ObjectId;

import dao.interfaces.EmployeeDAO;
import entity.Employee;
import javafx.util.Pair;
import utils.MongoConnect;
import utils.Security;

public class EmployeeDAOImpl implements EmployeeDAO {

  private Security security = new Security();
  MongoCollection<Document> employees;

  private void getCollection() {
    employees = MongoConnect.database.getCollection("employees");
  }

  Document newDoc(Employee employee) {
    Document worker = new Document("username", employee.getUsername())
      .append("active", employee.getActive())
      .append("email", employee.getEmail())
      .append("fullname", employee.getFullname())
      .append(
        "password",
        security.encryptPassword(employee.getPassword())
      )
      .append("role", employee.getRole())
      .append("telephoneNumber", employee.getTelephoneNumber())
      .append("bankDetails", employee.getBankDetails())
      .append("birthDate", employee.getBirthDate())
      .append("specialty", employee.getSpecialty());
    return worker;
  }

  Employee newEmployee(Document doc) {
    String email = doc.getString("email");
    String username = doc.getString("username");

    Employee e = new Employee(email, username);

    e.setId(doc.getObjectId("_id"));
    e.setFullname(doc.getString("fullname"));
    e.setPassword(doc.getString("password"));
    e.setActive(doc.getBoolean("active"));
    e.setRole(doc.getString("role"));
    e.setTelephoneNumber(doc.getString("telephoneNumber"));
    e.setBankDetails(doc.getString("bankDetails"));
    e.setCreated(doc.getDate("created"));
    e.setBirthDate(doc.getDate("birthDate"));
    e.setSpecialty(doc.getString("specialty"));

    return e;
  }

  @Override
  public void insert(Employee employee) {
    Document worker = newDoc(employee);

    worker.put("_id", new ObjectId());
    worker.put("created", new Date());

    getCollection();
    employees.insertOne(worker);
  }

  @Override
  public List<Employee> getAllEmployees() {
    List<Employee> eList = new ArrayList<Employee>();
    getCollection();

    MongoCursor<Document> cursor = employees.find().iterator();

    try {
      while (cursor.hasNext()) {
        eList.add(newEmployee(cursor.next()));
      }
    } finally {
      cursor.close();
    }

    return eList;
  }

  @Override
  public Employee findByID(String id) {
    Document query = new Document();
    getCollection();
    try {
      query = employees.find(new Document("_id", new ObjectId(id))).first();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return newEmployee(query);
  }

  @Override
  public List<Employee> findByField(String field, String data) {
    List<Employee> eList = new ArrayList<Employee>();
    getCollection();
    Pattern regex = Pattern.compile(data, Pattern.CASE_INSENSITIVE);
    MongoCursor<Document> cursor = employees
      .find(new BasicDBObject(field, regex))
      .iterator();

    try {
      while (cursor.hasNext()) {
        eList.add(newEmployee(cursor.next()));
      }
    } finally {
      cursor.close();
    }

    return eList;
  }

  @Override
  public List<Employee> findByDate(String field, Date dateGte, Date dateLt) {
    BasicDBObject betweenDates = new BasicDBObject(
      field,
      new Document("$gte", dateGte).append("$lte", dateLt)
    );
    getCollection();

    List<Employee> eList = new ArrayList<Employee>();

    MongoCursor<Document> cursor = employees.find(betweenDates).iterator();

    try {
      while (cursor.hasNext()) {
        eList.add(newEmployee(cursor.next()));
      }
    } finally {
      cursor.close();
    }

    return eList;
  }

  @Override
  public String findLoginData(String username, String password) {
    Document query = new Document();
    getCollection();
    try {
      query = employees.find(new Document("username", username)).first();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (query != null) {
      String cipher = security.encryptPassword(password);
      if (cipher.equals(query.get("password"))) {
        return query.get("role") == null ? "" : query.get("role").toString();
      } else {
        return "401 - Unauthorized";
      }
    }
    return "400 - Bad Request";
  }

  @Override
  public boolean findToCreateUser(String username, String email) {
    getCollection();
    try {
      if (
        employees.find(new Document("username", username)).first() == null &&
        employees.find(new Document("email", email)).first() == null
      ) {
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public List<Pair<String, String>> getAllIdAndNames() {
    List<Pair<String, String>> cbList = new ArrayList<Pair<String, String>>();
    getCollection();
    MongoCursor<Document> cursor = employees.find().iterator();

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
  public void update(String id, Employee employee) {
    Document worker = newDoc(employee);
    worker.put("updated", new Date());
    worker.remove("role");

    getCollection();
    employees.updateOne(
      new BasicDBObject("_id", new ObjectId(id)),
      new BasicDBObject("$set", worker)
    );
  }

  @Override
  public void updateField(String id, String field, String data) {
    BasicDBObject updatedData = new BasicDBObject(
      "$set",
      new BasicDBObject(field, data).append("updated", new Date())
    );

    getCollection();
    employees.updateOne(
      new BasicDBObject("_id", new ObjectId(id)),
      updatedData
    );
  }

  @Override
  public void updatePassword(String id, String data) {
    BasicDBObject updatedData = new BasicDBObject(
      "password",
      security.encryptPassword(data)
    );
    updatedData.append("updated", new Date());

    if (data.equals("12345")) {
      updatedData.append("defaultPassword", true);
    } else {
      updatedData.append("defaultPassword", false);
    }

    getCollection();
    employees.updateOne(
      new BasicDBObject("_id", new ObjectId(id)),
      new BasicDBObject("$set", updatedData)
    );
  }

  @Override
  public String findAndUpdatePassword(String username, String password) {
    Document query = new Document();
    getCollection();
    try {
      query = employees.find(new Document("username", username)).first();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (query != null) {
      if (
        query.containsKey("defaultPassword") ||
        query.getBoolean("defaultPassword")
      ) {
        updatePassword(query.getObjectId("_id").toString(), password);
        return query.getString("role");
      }
    }
    return "401 - Unauthorized";
  }

  @Override
  public void delete(String id) {
    getCollection();
    employees.deleteOne(new BasicDBObject("_id", new ObjectId(id)));
  }
}
