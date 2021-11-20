package control;

import dao.impl.ParametersDAOImpl;
import dao.interfaces.ParametersDAO;

public class DashboardControl {

  public void readPermissions(String role) {
    switch (role) {
      case "admin":
        // render all buttons
        break;
      case "doctor":
        // render patients, schedule
        break;
      case "receptionist": //assistant?
        // render clients, adoptions, schedule
        break;
      default:
        break;
    }
  }
	private ParametersDAO service = new ParametersDAOImpl();

	public String getRandomPhrase() {
		return service.getRandomPhrase();
	}
}

