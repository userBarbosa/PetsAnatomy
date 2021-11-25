package entity;

import java.util.Date;

import org.bson.types.ObjectId;

public class Owner {

  String email, fullname, telephoneNumber, identificationNumber, address, patientsName;
  Date created, updated, lastVisit;
  ObjectId id;

  public Owner(String fullname, String email, String identificationNumber) {
    this.fullname = fullname;
    this.email = email;
    this.identificationNumber = identificationNumber;
    this.created = new Date();
    this.lastVisit = null;
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

  public String getTelephoneNumber() {
    return this.telephoneNumber;
  }

  public void setTelephoneNumber(String telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }

  public String getIdentificationNumber() {
    return this.identificationNumber;
  }

  public void setIdentificationNumber(String identificationNumber) {
    this.identificationNumber = identificationNumber;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
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

  public Date getLastVisit() {
    return this.lastVisit;
  }

  public void setLastVisit(Date lastVisit) {
    this.lastVisit = lastVisit;
  }

  public ObjectId getId() {
    return this.id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getPatientsName() {
    return this.patientsName;
  }

  public void setPatientsName(String patientsName) {
    this.patientsName = patientsName;
  }
  
}
