package dao;

import java.util.Date;
import java.util.List;

import entity.Patient;
import javafx.util.Pair;

public interface PatientDAO {
	
	void insert(Patient patient, String ownerId);
	Patient findByID(String field, String id);
	List<Patient> findByField(String field, String data);
	List<Patient> findByDate(String field, Date dateGte, Date dateLte);
	List<Patient> getAllPatients();
	List<Pair<String, String>> getAllIdAndNames();
	void update(String id, Patient patient);
	void delete(String id);
	
}
