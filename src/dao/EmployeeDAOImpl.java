package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import entity.Employee;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import security.Config;

public class EmployeeDAOImpl implements EmployeeDAO {

  MongoCollection<Document> employees;

  public EmployeeDAOImpl() {
    connection();
  }

  void connection() {
    MongoConnect mc = new MongoConnect();
    employees = mc.database.getCollection("employees");
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

  Employee newEmployee(Document doc) {
    String email = doc.getString("email");
    String username = doc.getString("username");
    String fullname = doc.getString("fullname");
    String password = doc.getString("password"); //keep it? i think we should remove the password from the constructor...

    Employee e = new Employee(email, username, fullname, password);

    e.setId(doc.getObjectId("_id"));
    e.setActive(doc.getBoolean("active"));
    e.setRole(doc.getString("role"));
    e.setTelephoneNumber(doc.getString("telephoneNumber"));
    e.setBankDetails(doc.getString("bankDetails"));
    e.setCreated(doc.getDate("created"));
    e.setBirthDate(doc.getDate("birthDate"));
    List<String> l = doc.getList("speciality", String.class);
    for (String s : l) {
      e.addSpecialty(s);
    }
    return e;
  }

  public void insert(Employee employee) {
    Document worker = newDoc(employee);

    worker.put("_id", new ObjectId());
    worker.put("created", new Date());

    employees.insertOne(worker);
  }

  public Employee findByID(ObjectId id) {
    Document query = new Document();
    try {
      query = employees.find(new Document("_id", id)).first();
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

  public boolean findLoginData(String username, String password) {
    try {
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
      if (
        employees.find(new Document("username", username)) != null &&
        employees.find(new Document("email", email)) != null
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

  public List<Employee> returnAll() {
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

  public void update(ObjectId id, Employee employee) {
    Document worker = newDoc(employee);
    worker.put("updated", new Date());

    employees.updateOne(
      new BasicDBObject("_id", id),
      new BasicDBObject("$set", worker)
    );
  }

  public void delete(ObjectId id) {
    employees.deleteOne(Filters.eq("_id", id));
  }
}
