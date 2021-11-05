package entity;

import java.util.Date;
import org.bson.types.ObjectId;

public class Patient {

	ObjectId id, ownerId;
	String name, species, family, bloodtype, obs;
	Date birthdate, lastVisit, created, updated;
	boolean treatment;

	public Patient(
			String name,
			ObjectId ownerId,
			String species,
			String family
			) {
		this.ownerId = ownerId;
		this.name = name;
		this.species = species;
		this.family = family;
		this.created = new Date();
	}

	public Date getCreated() {
		return this.created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public ObjectId getId() {
		return this.id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ObjectId getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(ObjectId ownerId) {
		this.ownerId = ownerId;
	}

	public String getSpecies() {
		return this.species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getFamily() {
		return this.family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getBloodtype() {
		return this.bloodtype;
	}

	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getLastVisit() {
		return this.lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

	public boolean isTreatment() {
		return this.treatment;
	}

	public boolean getTreatment() {
		return this.treatment;
	}

	public void setTreatment(boolean treatment) {
		this.treatment = treatment;
	}
	
}
