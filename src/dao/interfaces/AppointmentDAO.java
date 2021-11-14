package dao.interfaces;

import java.util.Date;
import java.util.List;

import entity.Appointment;

public interface AppointmentDAO {
	void insert(Appointment appointment);
	Appointment findByID(String id);
	boolean findScheduleAppointment(Date date, String docId); 
	List<Appointment> findByField(String field, String data);
	List<Appointment> findByDate(String field, Date dateGte, Date dateLte);
	List<Appointment> getAllAppointments();
	void update(String id, Appointment appointment);
	void delete(String id);
}
