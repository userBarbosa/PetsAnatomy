package control;

import dao.AppointmentDAO;
import dao.AppointmentDAOImpl;
import entity.Appointment;
import org.bson.types.ObjectId;

public class AppointmentControl {

	private AppointmentDAO service = new AppointmentDAOImpl();

	public void create(Appointment appointment) {}

	public void read() {}

	public void update(ObjectId id, Appointment appointment) {}

	public void delete(ObjectId id) {}
	
}
