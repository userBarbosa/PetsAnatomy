package dao;

import entity.Employee;

public interface EmployeeDAO {
	
	void insert(Employee employee);
	void update(String id, Employee employee);
	void delete(String id);
    
}