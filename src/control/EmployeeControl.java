package control;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import entity.Employee;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public class EmployeeControl {

	private EmployeeDAO service = new EmployeeDAOImpl();

	/* tests structures; remove for final application:
  public static void main(String[] args) {
    EmployeeControl ec = new EmployeeControl();
    Employee marcos = new Employee(
      "abc@email.com",
      "Marcos",
      "Marcos F R Barbosa",
      "12345"
    );
    marcos.setId(new ObjectId());
    ec.create(marcos);
    ec.readByID(new ObjectId("6178893359bd6c23d3e59b27"));
    ec.readByDate(
      "created",
      new Date().from(Instant.now().minusMillis((86400000))),
      new Date().from(Instant.now().plusMillis(86400000))
    );
    ec.readByField("fullname", "Marcos Barbosa");
    ec.update(new ObjectId("6178893359bd6c23d3e59b27"), marcos);
    ec.delete(new ObjectId("6178893359bd6c23d3e59b27"));
    ec.readAll();
  }  */

	public void create(Employee employee) {
		service.insert(employee);
	}

	public void readAll() {
		List<Document> allWorkers = service.returnAll();
		if (allWorkers != null) {
			for (Document each : allWorkers) {
				System.out.println("All: " + each.get("fullname"));
			}
		} else {
			System.err.println("There are no workers in database");
		}
	}

	public void readByID(ObjectId id) {
		Document query = service.findByID(id);
		System.out.println("ById: " + query);
	}

	public void readByDate(String field, Date dateGte, Date dateLte) {
		List<Document> query = service.findByDate(field, dateGte, dateLte);

		if (query != null) {
			for (Document each : query) {
				System.out.println("ByDate: " + each.get("_id"));
			}
		} else {
			System.err.println("There are no workers in the specified dates");
		}
	}

	public void readByField(String field, String data) {
		Document query = service.findByField(field, data);
		System.out.println("ByField: " + query);
	}

	public void update(ObjectId id, Employee employee) {
		service.update(id, employee);
	}

	public void delete(ObjectId id) {
		service.delete(id);
	}
	
}
