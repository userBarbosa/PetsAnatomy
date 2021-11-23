package dao.interfaces;

import java.util.Date;
import java.util.List;

import entity.Patient;
import javafx.util.Pair;

public interface PatientDAO {
	
	void insert(Patient patient, String ownerId);
	Patient findByID(String field, String id);
	List<Patient> findByField(String field, String data);
	List<Patient> findByDate(String field, Date dateGte, Date dateLt);
	List<Patient> getAllPatients();
	List<String> getPetsByOwner(String ownerId);
	List<Pair<String, String>> getAllIdAndNames();
	void update(String id, Patient patient);
	void updateLastVisit(String id, Date date);
	void delete(String id);
	void deleteManyByOwnerId(String id);
}
