package dao;

import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

import entity.Appointment;

public interface AppointmentDAO {
	
	void insert(Appointment appointment);
	Document findByID(ObjectId id);
	Document findByField(String field, String data);
	List<Document> findByDate(String field, Date dateGte, Date dateLte);
	List<Document> returnAll();
	void update(String id, Appointment appointment);
	void delete(String id);
	
}
