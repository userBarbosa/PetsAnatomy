package dao.interfaces;

import java.util.Date;
import java.util.List;

import entity.Appointment;

public interface AppointmentDAO {
	void insert(Appointment appointment);
	Appointment findById(String id);
	List<Appointment> findManyById(String field, String id);
	boolean findScheduleAppointment(String field, String id, Date dateGte); 
	List<Appointment> findByField(String field, String data);
	List<Appointment> findByDate(String field, Date dateGte, Date dateLt);
	List<Appointment> getAllAppointments();
	void getNextAppointment(String employeeId);
	void update(String id, Appointment appointment);
	void updateField(String id, String field, String data);
	void delete(String id);
}
