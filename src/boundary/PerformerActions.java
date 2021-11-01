package boundary;

import java.io.FileNotFoundException;

public interface PerformerActions {
	
	 void performerActionAdmin(String role, String username, String password, String action) throws FileNotFoundException;
	 void performerActionEmployee(String role, String username, String password, String action) throws FileNotFoundException;
	 void performerActionDoctor(String role, String username, String password, String action) throws FileNotFoundException; 
	 
}