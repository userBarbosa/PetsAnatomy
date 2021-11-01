package dao;

import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import entity.Owner;

public interface OwnerDAO {

	void insert(Owner owner);
	Document findByID(ObjectId id);
	Document findByField(String field, String data);
	List<Document> findByDate(String field, Date dateGte, Date dateLte);
	List<Document> returnAll();
	void update(ObjectId id, Owner owner);
	void delete(ObjectId id);

}
