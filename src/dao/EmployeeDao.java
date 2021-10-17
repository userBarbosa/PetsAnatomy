package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entity.Employee;
import org.bson.Document;

public class EmployeeDao {

  MongoDatabase database;
  MongoCollection<Document> employees;

  void connection() {
    MongoConnect mc = new MongoConnect();
    database = mc.database;
    employees = database.getCollection("employees");
  }

  Document newDoc(Employee employee) {
    Document worker = new Document("fullname", employee.getFullname())
      .append("identificationNumber", "")
      .append("email", employee.getEmail())
      .append("telephoneNumber", employee.getTelephoneNumber())
      .append("password", employee.getPassword())
      .append("role", employee.getRole())
      .append("bankDetails", employee.getBankDetails())
      .append("active", employee.getActive())
      .append("created", employee.getCreated());
    return worker;
  }

  void insert(Employee employee) {
    Document worker = newDoc(employee);
    employees.insertOne(worker);
  }

  FindIterable<Document> findByField(String field, String data) {
    return employees.find(new Document(field, data));
  }

  FindIterable<Document> returnAll() {
    return employees.find();
  }

  void update(String id, Employee employee) {
    Document worker = newDoc(employee);
    employees.updateMany(Filters.eq("_id", id), worker);
  }

  void delete(String id) {
    employees.deleteOne(Filters.eq("_id", id));
  }
}