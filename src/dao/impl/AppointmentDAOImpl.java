package dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import dao.interfaces.AppointmentDAO;
import entity.Appointment;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import org.bson.Document;
import org.bson.types.ObjectId;
import utils.MongoConnect;

public class AppointmentDAOImpl implements AppointmentDAO {

  MongoCollection<Document> appointments;

  private void getCollection() {
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

  @Override
  public void insert(Appointment appointment) {
    Document app = newDoc(appointment);

    app.put("_id", new ObjectId());
    app.put("created", new Date());

    getCollection();
    appointments.insertOne(app);
  }

  @Override
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

  @Override
  public Appointment findById(String id) {
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

  @Override
  public List<Appointment> findByField(String field, String data) {
    List<Appointment> aList = new ArrayList<Appointment>();
    getCollection();
    Pattern regex = Pattern.compile(data, Pattern.CASE_INSENSITIVE);
    MongoCursor<Document> cursor = appointments
      .find(new BasicDBObject(field, regex))
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

  @Override
  public List<Appointment> findManyById(String field, String id) {
    List<Appointment> aList = new ArrayList<Appointment>();
    getCollection();
    MongoCursor<Document> cursor = appointments
      .find(new BasicDBObject(field, new ObjectId(id)))
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

  @Override
  public List<Appointment> findByDate(String field, Date dateGte, Date dateLt) {
    BasicDBObject betweenDates = new BasicDBObject(
      field,
      new Document("$gte", dateGte).append("$lte", dateLt)
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

  @Override
  public boolean findScheduleAppointment(
    String field,
    String id,
    Date dateGte
  ) {
    Document query = new Document();
    getCollection();

    Date dateLt = new Date(dateGte.getTime() + (30 * 60 * 1000));

    try {
      query =
        appointments
          .find(
            new Document(field, new ObjectId(id))
            .append(
                "date",
                new BasicDBObject("$gte", dateGte).append("$lt", dateLt)
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

  @Override
  public void update(String id, Appointment appointment) {
    Document appointed = newDoc(appointment);
    appointed.put("updated", new Date());

    getCollection();
    appointments.updateOne(
      new BasicDBObject("_id", new ObjectId(id)),
      new BasicDBObject("$set", appointed)
    );
  }

  @Override
  public void updateField(String id, String field, String data) {
    BasicDBObject updatedData = new BasicDBObject(
      "$set",
      new BasicDBObject(field, data).append("updated", new Date())
    );

    getCollection();
    appointments.updateOne(
      new BasicDBObject("_id", new ObjectId(id)),
      updatedData
    );
  }

  @Override
  public void getNextAppointment(String employeeId) {
    Date instantDate = new Date();
    Date result = instantDate;

    BasicDBObject query = new BasicDBObject(
      "employeeId",
      new ObjectId(employeeId)
    )
    .append("date", new BasicDBObject("$gte", instantDate));
    getCollection();

    MongoCursor<Document> cursor = appointments.find(query).iterator();
    try {
      while (cursor.hasNext()) {
        Date temp = cursor.next().getDate("date");
        if (result.after(temp)) {
          result = temp;
        }
      }
    } finally {
      cursor.close();
    }
  }

  @Override
  public void delete(String id) {
    getCollection();
    appointments.deleteOne(new BasicDBObject("_id", new ObjectId(id)));
  }
}
