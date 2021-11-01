package dao;

import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

import entity.Appointment;

public interface AppointmentDAO {
	
	void insert(Appointment appointment);
	Document findByID(ObjectId id);
	Document findByField();
	List<Document> findByDate();
	List<Document> returnAll();
	void update();
	void delete();
	
}
