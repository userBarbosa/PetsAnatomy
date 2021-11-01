package entity;

import java.util.Date;
import org.bson.types.ObjectId;

public class Appointment {

	Date date;
	ObjectId id, patientId, ownerId, employeeId;
	String obs;
	int state, financialState;
	double value;

	public Appointment(
			ObjectId employeeId,
			ObjectId patientId,
			ObjectId ownerId,
			Date date,
			double value
			) {
		this.date = date;
		this.patientId = patientId;
		this.ownerId = ownerId;
		this.employeeId = employeeId;
		this.value = value;
	}

	public ObjectId getPatientId() {
		return this.patientId;
	}

	public void setPatientId(ObjectId patientId) {
		this.patientId = patientId;
	}

	public int getFinancialState() {
		return this.financialState;
	}

	public void setFinancialState(int financialState) {
		this.financialState = financialState;
	}

	public double getValue() {
		return this.value;
	}

	public void setValue(double value) {
		this.value = value;
	}

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
