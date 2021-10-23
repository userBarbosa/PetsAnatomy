package dao;

import entity.Employee;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public interface EmployeeDAO {
  void insert(Employee employee);
  Document findByID(ObjectId id);
  Document findByField(String field, String data);
  List<Document> findByDate(String field, Date dateGte, Date dateLte);
  boolean findLoginData(String username, String password);
  boolean findToCreateUser(String username, String email);
  List<Document> returnAll();
  void update(ObjectId id, Employee employee);
  void delete(ObjectId id);
}
