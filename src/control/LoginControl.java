package control;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class LoginControl {

	private EmployeeDAO service = new EmployeeDAOImpl();

	public void login(String username, String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if (service.findLoginData(username, password)) {
			// SUCCESS
		} else {
			// FAILED
		}
	}
	
}
