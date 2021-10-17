package entity;

import java.util.Date;

import org.bson.types.ObjectId;

public class Appointment {
    Date date;
    ObjectId id, patientId, ownerId, employeeId; 
    String obs;
    int state;
    //telephone number?

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ObjectId getId() {
        return this.id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getpatientId() {
        return this.patientId;
    }

    public void setpatientId(ObjectId patientId) {
        this.patientId = patientId;
    }

    public ObjectId getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(ObjectId ownerId) {
        this.ownerId = ownerId;
    }

    public ObjectId getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(ObjectId employeeId) {
        this.employeeId = employeeId;
    }

    public String getObs() {
        return this.obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
