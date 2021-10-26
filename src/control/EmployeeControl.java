package control;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import entity.Employee;

import java.util.Date;

import org.bson.types.ObjectId;

public class EmployeeControl {

  private EmployeeDAO service = new EmployeeDAOImpl();

  public void create(Employee employee) {
    service.insert(employee);
  }

  public void readAll() {}

  public void readByID(ObjectId id) {}

  public void readByDate(String field, Date dateGte, Date dateLte) {}

  public void readByField(String field, String data) {}

  public void update(ObjectId id, Employee employee) {}

  public void delete(ObjectId id) {}
}
