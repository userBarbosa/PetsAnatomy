package dao;

import entity.Patient;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public interface PatientDAO {
  void insert(Patient patient, ObjectId ownerId);
  List<Document> findByDate(String field, Date dateGte, Date dateLte);
  Document findByID(String field, ObjectId id);
  Document findByField(String field, String data);
  List<Document> returnAll();
  void update(ObjectId id, Patient patient);
  void delete(ObjectId id);
}
