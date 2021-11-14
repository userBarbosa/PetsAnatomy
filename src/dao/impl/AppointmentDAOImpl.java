package dao.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import org.bson.Document;
import org.bson.types.ObjectId;

import dao.interfaces.AppointmentDAO;
import entity.Appointment;
import utils.MongoConnect;

public class AppointmentDAOImpl implements AppointmentDAO {

	MongoCollection<Document> appointments;

	public void getCollection() {
		appointments = MongoConnect.database.getCollection("appointments");
	}

	Document newDoc(Appointment appointment) {
		Document app = new Document("patientId", appointment.getPatientId())
				.append("ownerId", appointment.getOwnerId())
				.append("employeeId", appointment.getEmployeeId())
				.append("obs", appointment.getObs())
				.append("state", appointment.getState())
				.append("financialState", appointment.getFinancialState())
				.append("date", appointment.getDate())
				.append("value", appointment.getValue());
		return app;
	}

	Appointment newApp(Document doc) {
		ObjectId employeeId = doc.getObjectId("employeeId");
		ObjectId patientId = doc.getObjectId("patientId");
		ObjectId ownerId = doc.getObjectId("ownerId");
		Date date = doc.getDate("date");
		Double value = doc.getDouble("value");

		Appointment app = new Appointment(
				employeeId,
				patientId,
				ownerId,
				date,
				value
				);

		app.setId(doc.getObjectId("_id"));
		app.setObs(doc.getString("obs"));
		app.setState(doc.getInteger("state"));
		app.setFinancialState(doc.getInteger("financialState"));

		return app;
	}

	public void insert(Appointment appointment) {
		Document app = newDoc(appointment);

		app.put("_id", new ObjectId());
		app.put("created", new Date());

		getCollection();
		appointments.insertOne(app);
	}

	public void update(String id, Appointment appointment) {
		Document appointed = newDoc(appointment);
		appointed.put("updated", new Date());

		getCollection();
		appointments.updateOne(
				new BasicDBObject("_id", new ObjectId(id)),
				new BasicDBObject("$set", appointed)
				);
	}

	public void delete(String id) {
		getCollection();
		appointments.deleteOne(Filters.eq("_id", new ObjectId(id)));		
	}

	public List<Appointment> getAllAppointments() {
		List<Appointment> aList = new ArrayList<Appointment>();
		getCollection();

		MongoCursor<Document> cursor = appointments.find().iterator();

		try {
			while (cursor.hasNext()) {
				aList.add(newApp(cursor.next()));
			}
		} finally {
			cursor.close();
		}

		return aList;
	}

	public Appointment findByID(String id) {
		Document query = new Document();
		getCollection();
		try {
			query =
					appointments.find(new BasicDBObject("_id", new ObjectId(id))).first();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newApp(query);
	}

	public List<Appointment> findByField(String field, String data) {
		List<Appointment> aList = new ArrayList<Appointment>();
		getCollection();

		MongoCursor<Document> cursor = appointments
				.find(new BasicDBObject(field, data))
				.iterator();

		try {
			while (cursor.hasNext()) {
				aList.add(newApp(cursor.next()));
			}
		} finally {
			cursor.close();
		}

		return aList;
	}

	public List<Appointment> findByDate(String field, Date dateGte, Date dateLte) {
		BasicDBObject betweenDates = new BasicDBObject(
				field,
				new Document("$gte", dateGte).append("$lte", dateLte)
				);
		getCollection();

		List<Appointment> aList = new ArrayList<Appointment>();

		MongoCursor<Document> cursor = appointments.find(betweenDates).iterator();

		try {
			while (cursor.hasNext()) {
				aList.add(newApp(cursor.next()));
			}
		} finally {
			cursor.close();
		}

		return aList;
	}

	public boolean findScheduleAppointment(Date date, String docId) {
		Document query = new Document();
		getCollection();

		LocalDateTime dateGte = date
				.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();
		LocalDateTime dateLte = dateGte.plusMinutes(30);

		try {
			query =
					appointments
					.find(
							new Document("employeeId", new ObjectId(docId))
							.append(
									"date",
									new BasicDBObject("$gte", dateGte).append("$lte", dateLte)
									)
							)
					.first();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (query != null) {
			return true;
		}
		return false;
	}

}
