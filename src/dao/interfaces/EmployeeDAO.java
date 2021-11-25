package dao.interfaces;

import java.util.Date;
import java.util.List;

import entity.Employee;
import javafx.util.Pair;

public interface EmployeeDAO {
  void insert(Employee employee);
  Employee findByID(String id);
  List<Employee> findByField(String field, String data);
  List<Employee> findByDate(String field, Date dateGte, Date dateLt);
  String findLoginData(String username, String password);
  boolean findToCreateUser(String username, String email);
  String findAndUpdatePassword(String username, String password);
  List<Employee> getAllEmployees();
  List<Pair<String, String>> getAllIdAndNames();
  void update(String id, Employee employee);
  void updateField(String id, String field, String data);
  void updatePassword(String id, String data);
  void delete(String id);
}
