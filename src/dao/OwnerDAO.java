package dao;

import entity.Owner;

public interface OwnerDAO {
	
	void insert(Owner owner);
	void update(String id, Owner owner);
	void delete(String id);
    
}
