package dao;

import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import entity.Patient;

public interface PatientDAO {
	
	void insert(Patient patient, ObjectId ownerId);
	List<Document> findByDate( String field, Date dateGte, Date dateLte);
	void update(ObjectId id, Patient patient);
	void delete(ObjectId id);
	List<Document> returnAll();
	Document findByID(String field, ObjectId id);
	
}