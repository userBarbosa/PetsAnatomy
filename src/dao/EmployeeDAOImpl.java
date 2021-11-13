package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import entity.Employee;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.util.Pair;
import org.bson.Document;
import org.bson.types.ObjectId;

import utils.MongoConnect;
import utils.Security;

public class EmployeeDAOImpl implements EmployeeDAO {

  MongoCollection<Document> employees;

  public EmployeeDAOImpl() {
    connection();
  }

  void connection() {
    MongoConnect mc = new MongoConnect();
    mc.connection();
    employees = mc.database.getCollection("employees");
  }

  Document newDoc(Employee employee) {
    Document worker = new Document("username", employee.getUsername())
      .append("active", employee.getActive())
      .append("email", employee.getEmail())
      .append("fullname", employee.getFullname())
      .append("password", new Security().encryptPassword(employee.getPassword()))
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

  public void insert(Employee employee) {
    Document worker = newDoc(employee);

    worker.put("_id", new ObjectId());
    worker.put("created", new Date());

    employees.insertOne(worker);
  }

  public Employee findByID(String id) {
    Document query = new Document();
    try {
      query = employees.find(new Document("_id", new ObjectId(id))).first();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return newEmployee(query);
  }

  public List<Employee> findByField(String field, String data) {
    List<Employee> eList = new ArrayList<Employee>();

    // String regexQuery = "/^" + data + "/";
    MongoCursor<Document> cursor = employees
      .find(new Document(field, data))
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

  public String findLoginData(String username, String password) {
    Document query = new Document();
    try {
      query = employees.find(new Document("username", username)).first();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (query != null) {
      String cipher = new Security().encryptPassword(password);
      if (cipher.equals(query.get("password"))) {
        return query.get("role") == null ? "" : query.get("role").toString();
      } else {
        return "401 - Unauthorized";
      }
    }
    return "400 - Bad Request";
  }

  public boolean findToCreateUser(String username, String email) {
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

  public List<Employee> findByDate(String field, Date dateGte, Date dateLte) {
    BasicDBObject betweenDates = new BasicDBObject(
      field,
      new Document("$gte", dateGte).append("$lte", dateLte)
    );

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

  public List<Employee> getAllEmployees() {
    List<Employee> eList = new ArrayList<Employee>();

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

  public void update(String id, Employee employee) {
    Document worker = newDoc(employee);
    worker.put("updated", new Date());

    employees.updateOne(
      new BasicDBObject("_id", new ObjectId(id)),
      new BasicDBObject("$set", worker)
    );
  }

  public void delete(String id) {
    employees.deleteOne(Filters.eq("_id", new ObjectId(id)));
  }

  public List<Pair<String, String>> getAllIdAndNames() {
    List<Pair<String, String>> cbList = new ArrayList<Pair<String, String>>();

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
}
