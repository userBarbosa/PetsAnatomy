package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import entity.Patient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public class PatientDAOImpl implements PatientDAO {

	MongoCollection<Document> patients;

	public PatientDAOImpl() {
		connection();
	}

	void connection() {
		MongoConnect mc = new MongoConnect();
		patients = mc.database.getCollection("patients");
	}

	Document newDoc(Patient patient) {
		Document pat = new Document("ownerId", patient.getOwnerId())
				.append("name", patient.getName())
				.append("species", patient.getSpecies())
				.append("family", patient.getFamily())
				.append("bloodtype", patient.getBloodtype())
				.append("obs", patient.getObs())
				.append("birthdate", patient.getBirthdate())
				.append("lastVisit", patient.getLastVisit())
				.append("treatment", patient.getTreatment());
		return pat;
	}

	public void insert(Patient patient, ObjectId ownerId) {
		Document pat = newDoc(patient);

		pat.put("_id", new ObjectId());
		pat.put("created", new Date());
		pat.replace("ownerId", ownerId);

		patients.insertOne(pat);
	}

	public Document findByField(String field, String data) {
		try {
			Document query = patients.find(new Document(field, data)).first();
			return query;
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}

	public Document findByID(String field, ObjectId objectId) {
		try {
			Document query = patients.find(new Document(field, objectId)).first();
			return query;
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}

	public List<Document> returnAll() {
		List<Document> query = new ArrayList<Document>();

		MongoCursor<Document> cursor = patients.find().iterator();

		try {
			while (cursor.hasNext()) {
				query.add(cursor.next());
			}
		} finally {
			cursor.close();
		}
		return query;
	}

	public List<Document> findByDate(String field, Date dateGte, Date dateLte) {
		BasicDBObject betweenDates = new BasicDBObject(
				field,
				new Document("$gte", dateGte).append("$lte", dateLte)
				);

		List<Document> docPat = new ArrayList<Document>();

		MongoCursor<Document> cursor = patients.find(betweenDates).iterator();

		try {
			while (cursor.hasNext()) {
				docPat.add(cursor.next());
			}
		} finally {
			cursor.close();
		}

		return docPat;
	}

	public void update(ObjectId id, Patient patient) {
		Document pat = newDoc(patient);
		pat.put("updated", new Date());

		BasicDBObject update = new BasicDBObject("$set", pat);

		patients.updateOne(new BasicDBObject("_id", id), update);
	}

	public void delete(ObjectId id) {
		patients.deleteOne(Filters.eq("_id", id));
	}
	
}
