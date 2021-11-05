package dao;

import entity.Employee;
import java.util.Date;
import java.util.List;
import javafx.util.Pair;
import org.bson.types.ObjectId;

public interface EmployeeDAO {
  void insert(Employee employee);
  Employee findByID(String id);
  List<Employee> findByField(String field, String data);
  List<Employee> findByDate(String field, Date dateGte, Date dateLte);
  // boolean findLoginData(String username, String password);
  String findLoginData(String username, String password);
  boolean findToCreateUser(String username, String email);
  List<Employee> returnAll();
  void update(String id, Employee employee);
  void delete(String id);
  List<Pair<String, String>> allEmployees();
}
