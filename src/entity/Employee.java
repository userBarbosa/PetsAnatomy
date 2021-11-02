package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

public class Employee {

	ObjectId id;
	boolean active;
	String email, username, fullname, password, role, telephoneNumber, bankDetails;
	Date created, birthDate;
	List<String> specialty = new ArrayList<String>();
	
	public Employee() { super(); }

	public Employee(
			String email,
			String username,
			String fullname,
			String password
			) {
		this.email = email;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.created = new Date();
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setSpecialty(List<String> specialty) {
		this.specialty = specialty;
	}

	public List<String> getSpecialty() {
		return this.specialty;
	}

	public void addSpecialty(String specialtyToAdd) {
		this.specialty.add(specialtyToAdd);
	}

	public ObjectId getId() {
		return this.id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public boolean isActive() {
		return this.active;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTelephoneNumber() {
		return this.telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getBankDetails() {
		return this.bankDetails;
	}

	public void setBankDetails(String bankDetails) {
		this.bankDetails = bankDetails;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
}
