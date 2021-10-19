package control;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import entity.Employee;
import org.bson.types.ObjectId;

public class EmployeeControl {

  private EmployeeDAO service = new EmployeeDAOImpl();

  public void create(Employee employee) {}

  public void read() {}

  public void update(ObjectId id, Employee employee) {}

  public void delete(ObjectId id) {}
}
