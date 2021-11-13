package dao.impl;

import java.util.Date;
import java.util.List;

import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.bson.types.ObjectId;

import dao.interfaces.AppointmentDAO;
import entity.Appointment;
import utils.MongoConnect;

public class AppointmentDAOImpl implements AppointmentDAO {

  MongoCollection<Document> appointments;
  MongoConnect mc = new MongoConnect();

  public void getCollection() {
    appointments = mc.database.getCollection("appointments");
  }

  /* 	Date date;
  ObjectId id, patientId, ownerId, employeeId;
  String obs;
  int state, financialState;
  double value; */

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

  public void insert(Appointment appointment) {
    Document app = newDoc(appointment);

    app.put("_id", new ObjectId());
    app.put("created", new Date());
    getCollection();
    appointments.insertOne(app);
  }

  public Document findByID(ObjectId id) {
    getCollection();
    try {
      Document query = appointments.find(new Document("_id", id)).first();
      return query;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<Document> returnAll() {
    return null;
  }

  public Document findByField(String field, String data) {
    return null;
  }

  public List<Document> findByDate(String field, Date dateGte, Date dateLte) {
    return null;
  }


  

  public void delete(String id) {

  }

  @Override
  public void update(String id, Appointment appointment) {
    
  }
}
